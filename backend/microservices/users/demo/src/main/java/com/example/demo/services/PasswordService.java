package com.example.demo.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8); // Generate an 8-character random password
    }
}