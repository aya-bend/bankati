package com.example.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.authentication.DTO.CreateUserRequest;
import com.example.authentication.Service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthService authService;


    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        authService.createUser(request);
        return ResponseEntity.ok("User created successfully");
    }

}