package com.example.wallet.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.wallet.DTO.DepositRequest;
import com.example.wallet.models.Client;
import com.example.wallet.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    @Autowired
    private ClientService clientService;

    // endpoint to create a new client with balance = 0
    @PostMapping
    public ResponseEntity<String> createClient(@RequestParam Long id) {
        clientService.createClient(id);
        return ResponseEntity.ok("Client created successfully with balance 0.");
    }

    //endpoint to retrieve client info
    @GetMapping("/profile/{id}")
    public ResponseEntity<Client> getAdminProfile(@PathVariable Long id) {
        // Fetch the admin profile by ID
        Client clientProfile = clientService.getClientById(id);

        // Return the admin details
        return ResponseEntity.ok(clientProfile);
    }

    //end^point to add amount the balance : deposit process
    @PostMapping("/deposit")
    public ResponseEntity<Map<String, String>> deposit(@RequestBody DepositRequest depositRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            clientService.deposit(depositRequest.getUserId(), depositRequest.getAmount());
            response.put("message", "Deposit successful");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("message", "Error while deposit : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
}
