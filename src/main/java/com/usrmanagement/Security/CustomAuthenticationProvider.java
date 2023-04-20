package com.usrmanagement.Security;

import com.usrmanagement.model.User;
import com.usrmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        if(passwordEncoder.matches(password,user.getPassword())){
            return  new UsernamePasswordAuthenticationToken(email,password,new ArrayList<>());
        }else{
            throw  new BadCredentialsException("Invalid credentials");
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
