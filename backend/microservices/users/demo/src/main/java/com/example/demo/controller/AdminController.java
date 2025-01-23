package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Admin;
import com.example.demo.services.AdminService;

@RestController
@RequestMapping("/api/back/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //endpoint to retrieve admin info for profile page
    @GetMapping("/profile/{id}")
    public ResponseEntity<Admin> getAdminProfile(@PathVariable Long id) {
        // Fetch the admin profile by ID
        Admin adminProfile = adminService.getAdminById(id);

        // Return the admin details
        return ResponseEntity.ok(adminProfile);
    }
}