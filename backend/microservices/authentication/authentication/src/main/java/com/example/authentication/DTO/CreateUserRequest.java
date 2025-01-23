package com.example.authentication.DTO;

import com.example.authentication.model.Role;

import lombok.Data;

@Data
public class CreateUserRequest {
    private Long id;
    private String username;
    private String password;
    private Role roles;
}