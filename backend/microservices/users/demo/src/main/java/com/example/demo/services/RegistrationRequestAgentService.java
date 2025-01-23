package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.DTO.CreateUserRequest;
import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.models.RegistrationStatus;
import com.example.demo.models.Role;
import com.example.demo.repository.RegistrationRequestAgentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class RegistrationRequestAgentService {

    @Autowired
    private RegistrationRequestAgentRepository requestRepository;

    @Autowired
    private AgentService agentService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordService passwordService;

    @Value("${auth.service.url}")
    private String authServiceUrl; // Base URL for the Authentication service




    // Méthode pour récupérer les demandes avec statut PENDING
    public List<RegistrationRequestAgent> getPendingRequests() {
        return requestRepository.findByStatus(RegistrationStatus.PENDING);
    }

    // Méthode pour ajouter une nouvelle demande
    public RegistrationRequestAgent addRequest(RegistrationRequestAgent request) {
        // Le statut est déjà initialisé par défaut à "PENDING" dans l'entité
        return requestRepository.save(request);
    }

    // Méthode pour rejeter une demande
    public RegistrationRequestAgent rejectRequest(Long requestId) {
        RegistrationRequestAgent request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));
        
        // Mettre à jour le statut
        request.setStatus(RegistrationStatus.REJECTED);
        return requestRepository.save(request);
    }

    // method to accept a registration request : the status column become 'ACCEPTED' and the agent is added to the table 'AGENT'
    @Transactional
    public void AcceptRequest(Long requestId) {
        RegistrationRequestAgent request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));

        // Generate a random password
        String generatedPassword = passwordService.generateRandomPassword();

        // Prepare the DTO for the Authentication microservice
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setId(request.getId());
        userRequest.setUsername(request.getPhone()); // Use phone as username
        userRequest.setPassword("123456");  // Set the generated password
        userRequest.setRoles(Role.AGENT);       // Assign role as AGENT
        System.out.println(userRequest);

        HttpEntity<CreateUserRequest> requestEntity = new HttpEntity<>(userRequest);

        try {
            System.out.println("Sending request to Authentication microservice...");
            System.out.println("Auth Service URL: " + authServiceUrl + "/create-user");
            System.out.println("Request Body: " + new ObjectMapper().writeValueAsString(userRequest));

            ResponseEntity<String> response = restTemplate.postForEntity(authServiceUrl + "/create-user", requestEntity, String.class);

            System.out.println("Response from Authentication service: " + response.getBody());
        } catch (HttpClientErrorException ex) {
            System.err.println("Error Response Body: " + ex.getResponseBodyAsString());
            throw ex;
        } catch (Exception ex) {
            System.err.println("Exception occurred: " + ex.getMessage());
            
        }
        
        // Mettre à jour le statut
        request.setStatus(RegistrationStatus.ACCEPTED);
        requestRepository.save(request);

        // Optional: Log or communicate the credentials back to the requester (e.g., via email or SMS)
        System.out.println("Generated credentials for agent: " + userRequest.getUsername() + " / " + generatedPassword);

         // Create an agent from the registration request
         agentService.createAgentFromRequest(request);
    }
}
