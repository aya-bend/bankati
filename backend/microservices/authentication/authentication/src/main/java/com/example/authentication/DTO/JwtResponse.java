package com.example.authentication.DTO;

import com.example.authentication.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JwtResponse {
    private Long id;
    private String token;
    private String username;
    private Role roles;
}
