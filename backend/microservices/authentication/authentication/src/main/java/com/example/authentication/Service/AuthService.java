package com.example.authentication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.authentication.DTO.CreateUserRequest;
import com.example.authentication.Repository.UserRepository;
import com.example.authentication.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    //method to create a user
    public void createUser(CreateUserRequest request) {
        // Business logic to create a user
        User user = new User();
        user.setId(request.getId());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password before saving
        user.setRoles(request.getRoles());
        userRepository.save(user);
    }
}
