package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Admin;
import com.example.demo.models.Agent;
import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.repository.AgentRepository;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    //method to return all the agents
    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }

    // method to create a new agent from the registration request
    public Agent createAgentFromRequest(RegistrationRequestAgent request) {
        Agent newAgent = new Agent();
        newAgent.setId(request.getId());
        newAgent.setFirstName(request.getFirstName());
        newAgent.setLastName(request.getLastName());
        newAgent.setEmail(request.getEmail());
        newAgent.setPhone(request.getPhone());
        newAgent.setAddress(request.getAddress());
        newAgent.setIdType(request.getIdType());
        newAgent.setIdNumber(request.getIdNumber());
        newAgent.setBirthDate(request.getBirthDate());
        newAgent.setImmatriculation(request.getImmatriculation());
        newAgent.setPatentNumber(request.getPatentNumber());
        return agentRepository.save(newAgent);
    }

    // method to delete Agent
    public void deleteAgent(Long id) {
        if (agentRepository.existsById(id)) {
            agentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Agent with ID " + id + " not found.");
        }
    }

    //method to modify an agent
    public Agent updateAgent(Long id, Agent updatedAgent) {
        Optional<Agent> existingAgentOptional = agentRepository.findById(id);
        if (existingAgentOptional.isPresent()) {
            Agent existingAgent = existingAgentOptional.get();

            // Update the fields of the existing agent with the values from updatedAgent
            existingAgent.setFirstName(updatedAgent.getFirstName());
            existingAgent.setLastName(updatedAgent.getLastName());
            existingAgent.setEmail(updatedAgent.getEmail());
            existingAgent.setPhone(updatedAgent.getPhone());
            existingAgent.setAddress(updatedAgent.getAddress());
            existingAgent.setBirthDate(updatedAgent.getBirthDate());
            existingAgent.setImmatriculation(updatedAgent.getImmatriculation());
            existingAgent.setPatentNumber(updatedAgent.getPatentNumber());
            existingAgent.setIdType(updatedAgent.getIdType());
            existingAgent.setIdNumber(updatedAgent.getIdNumber());

            return agentRepository.save(existingAgent);
        } else {
            throw new IllegalArgumentException("Agent with ID " + id + " not found.");
        }
    }

    // method to retrieve agent infos for profile page
    public Agent getAgentById(Long id) {
        // Retrieve user by username
        return agentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id : " + id));
    }
}