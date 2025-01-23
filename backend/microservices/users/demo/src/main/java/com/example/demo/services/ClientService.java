package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UpdateClientDTO;
import com.example.demo.models.Agent;
import com.example.demo.models.Client;
import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.models.RegistrationRequestClient;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    //get all clients 
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    // method to delete Client
    public void deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Agent with ID " + id + " not found.");
        }
    }

    // method to update client
    public Client updateClient(Long id, UpdateClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with ID " + id + " not found."));

        // Update only the fields from the DTO
        client.setClientType(dto.getClientType());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());

        return clientRepository.save(client);
    }

    // method to create a new client from the registration request
    public Client createClientFromRequest(RegistrationRequestClient request) {
        Client newClient = new Client();
        newClient.setId(request.getId());
        newClient.setFirstName(request.getFirstName());
        newClient.setLastName(request.getLastName());
        newClient.setEmail(request.getEmail());
        newClient.setPhone(request.getPhone());
        newClient.setClientType(request.getClientType());

        return clientRepository.save(newClient);
    }

    // service to retrive client info
    public Optional<Client> getUserById(Long id) {
        return clientRepository.findById(id);
    }
    
}
