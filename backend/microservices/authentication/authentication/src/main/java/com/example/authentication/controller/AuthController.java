package com.example.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.authentication.DTO.JwtResponse;
import com.example.authentication.DTO.LoginRequest;
import com.example.authentication.Repository.UserRepository;
import com.example.authentication.model.Role;
import com.example.authentication.model.User;
import com.example.authentication.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user
        System.out.println("111111");
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        // Set authentication context
        System.out.println("222222");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT
        System.out.println("33333");
        String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());

        // Get role (assuming single role) and map it to your Role enum
        System.out.println("44444");
        // Fetch user details
        
        User user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Get role (assuming single role) and map it to your Role enum
        String roleName = authentication.getAuthorities().iterator().next().getAuthority();
        Role role = Role.valueOf(roleName); // Convert to Role enum

        // Return response
        System.out.println("555555");
        return new JwtResponse(user.getId(), jwt, loginRequest.getUsername(), role);
    }
    
}