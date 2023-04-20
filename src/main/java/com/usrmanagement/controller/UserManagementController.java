package com.usrmanagement.controller;

import com.usrmanagement.Dto.LoginDto;
import com.usrmanagement.model.User;
import com.usrmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?>  register (@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginDto loginDto) throws Exception {
        return userService.login(loginDto);
    }


}
