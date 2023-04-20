package com.usrmanagement.service;

import com.usrmanagement.Dto.LoginDto;
import com.usrmanagement.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> register(User user);

    ResponseEntity<?> login(LoginDto loginDto) throws Exception;
}
