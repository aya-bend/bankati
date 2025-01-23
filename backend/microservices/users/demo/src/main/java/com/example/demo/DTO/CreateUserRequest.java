package com.example.demo.DTO;

import com.example.demo.models.Role;

import lombok.Data;

@Data
public class CreateUserRequest {
    private Long id;
    private String username;
    private String password;
    private Role roles;
}