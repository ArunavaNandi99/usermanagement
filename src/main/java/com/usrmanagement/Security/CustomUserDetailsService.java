package com.usrmanagement.Security;

import com.usrmanagement.model.User;
import com.usrmanagement.repository.UserRepo;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService {

    private UserRepo userRepo;

    public UserDetails loadUserByEmail(String email)throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),new ArrayList<>());
    }
}
