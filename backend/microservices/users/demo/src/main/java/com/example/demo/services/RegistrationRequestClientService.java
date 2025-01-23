package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.demo.DTO.CreateUserRequest;
import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.models.RegistrationRequestClient;
import com.example.demo.models.RegistrationStatus;
import com.example.demo.models.Role;
import com.example.demo.repository.RegistrationRequestClientRepository;

@Service
public class RegistrationRequestClientService {
   
    @Autowired
    private RegistrationRequestClientRepository requestRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordService passwordService;

    @Value("${auth.service.url}")
    private String authServiceUrl; // Base URL for the Authentication service

    @Value("${wallet.service.url}")
    private String walletServiceUrl; // Base URL for the wallet service


    // method to fetch requests with status PENDING
    public List<RegistrationRequestClient> getPendingRequests() {
        return requestRepository.findByStatus(RegistrationStatus.PENDING);
    }

    // method to add a new request
    public RegistrationRequestClient addRequest(RegistrationRequestClient request) {
        // Le statut est déjà initialisé par défaut à "PENDING" dans l'entité
        return requestRepository.save(request);
    }

    // Method to reject a request
    public RegistrationRequestClient rejectRequest(Long requestId) {
        RegistrationRequestClient request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));
        
        // Mettre à jour le statut
        request.setStatus(RegistrationStatus.REJECTED);
        return requestRepository.save(request);
    }

    // method to accept a registration request : the status column become 'ACCEPTED' and the agent is added to the table 'AGENT'
    @Transactional
    public void AcceptRequest(Long requestId) {
        RegistrationRequestClient request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));

        // Generate a random password
        String generatedPassword = passwordService.generateRandomPassword();

        // Prepare the DTO for the Authentication microservice
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setId(request.getId());
        userRequest.setUsername(request.getPhone()); // Use phone as username
        //userRequest.setPassword(generatedPassword);  // Set the generated password
        userRequest.setPassword("123456");
        userRequest.setRoles(Role.CLIENT);       // Assign role as AGENT
        System.out.println(userRequest);

        restTemplate.postForEntity(authServiceUrl + "/create-user", userRequest, String.class);
        
        // Mettre à jour le statut
        request.setStatus(RegistrationStatus.ACCEPTED);
        requestRepository.save(request);

         // Create an agent from the registration request
         clientService.createClientFromRequest(request);

         // Call the wallet microservice to create a wallet entry
        restTemplate.postForEntity(walletServiceUrl + "?id=" + request.getId(), null, String.class);
    }
}
