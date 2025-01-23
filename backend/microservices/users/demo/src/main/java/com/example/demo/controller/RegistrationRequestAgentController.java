package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.services.RegistrationRequestAgentService;



@RestController
@RequestMapping("/api/back/registration-requests-agents")
public class RegistrationRequestAgentController {

    @Autowired
    private RegistrationRequestAgentService requestService;

    // Endpoint pour récupérer les demandes "PENDING"
    @GetMapping("/pending")
    public List<RegistrationRequestAgent> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    // Endpoint pour ajouter une nouvelle demande
    @PostMapping
    public ResponseEntity<RegistrationRequestAgent> addRequest(@RequestBody RegistrationRequestAgent request) {
        System.out.println("received request : "+ request);
        RegistrationRequestAgent newRequest = requestService.addRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRequest);
    }
    
    // Endpoint pour reject une demande
    @PutMapping("/{id}/reject")
    public ResponseEntity<RegistrationRequestAgent> rejectRequest(@PathVariable Long id) {
        RegistrationRequestAgent updatedRequest = requestService.rejectRequest(id);
        return ResponseEntity.ok(updatedRequest);
    }

    // Endpoint to accept a request
    @PutMapping("/{id}/accept")
    public ResponseEntity<Map<String, String>> acceptRequest(@PathVariable Long id) {
        requestService.AcceptRequest(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration request accepted successfully.");
        return ResponseEntity.ok(response);
    }
}