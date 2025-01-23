package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    // method to retrieve admin infos for profile page
    public Admin getAdminById(Long id) {
        // Retrieve user by username
        return adminRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id : " + id));
    }
}
