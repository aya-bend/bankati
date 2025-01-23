package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Admin;
import com.example.demo.models.Agent;
import com.example.demo.services.AgentService;

@RestController
@RequestMapping("/api/back/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    //endpoint to fetch the agent list
    @GetMapping
    public ResponseEntity<List<Agent>> getAllAgents(){
        List<Agent> agents = agentService.getAllAgents();
        return ResponseEntity.ok(agents);
    }

    // endpoint to delete an agent
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAgent(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            agentService.deleteAgent(id);
            response.put("message", "Agent deleted successfully.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("message", "Error while deleting agent : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //endpoint to update an agent
    @PutMapping("/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable Long id, @RequestBody Agent agent) {
        try {
            Agent updatedAgent = agentService.updateAgent(id, agent);
            return ResponseEntity.ok(updatedAgent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //endpoint to retrieve agent info for profile page
    @GetMapping("/profile/{id}")
    public ResponseEntity<Agent> getAgentProfile(@PathVariable Long id) {
        // Fetch the admin profile by ID
        Agent agentProfile = agentService.getAgentById(id);

        // Return the admin details
        return ResponseEntity.ok(agentProfile);
    }
    
}
