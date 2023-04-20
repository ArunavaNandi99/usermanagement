package com.usrmanagement.service;

import com.usrmanagement.Dto.LoginDto;
import com.usrmanagement.model.User;
import com.usrmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> register(User user) {

        User userDetails = new User();
        userDetails.setFname(user.getFname());
        userDetails.setLname(user.getFname());
        userDetails.setEmail(user.getEmail());
        userDetails.setPassword(passwordEncoder.encode((user.getPassword())));

        User saveUser = userRepo.save(userDetails);

        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            return new ResponseEntity<>("Invalid credentials, Please check username and password" , HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("login successfully",HttpStatus.OK);
    }
}
