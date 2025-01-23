package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.models.RegistrationRequestClient;
import com.example.demo.services.RegistrationRequestClientService;

@RestController
@RequestMapping("/api/back/registration-requests-clients")
public class RegistrationRequestClientController {

    @Autowired
    private RegistrationRequestClientService requestService;

    // Endpoint pour récupérer les demandes "PENDING"
    @GetMapping("/pending")
    public List<RegistrationRequestClient> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    // Endpoint pour ajouter une nouvelle demande
    @PostMapping
    public ResponseEntity<RegistrationRequestClient> addRequest(@RequestBody RegistrationRequestClient request) {
        System.out.println("received request : "+ request);
        RegistrationRequestClient newRequest = requestService.addRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRequest);
    }

    // Endpoint pour reject une demande
    @PutMapping("/{id}/reject")
    public ResponseEntity<RegistrationRequestClient> rejectRequest(@PathVariable Long id) {
        RegistrationRequestClient updatedRequest = requestService.rejectRequest(id);
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
