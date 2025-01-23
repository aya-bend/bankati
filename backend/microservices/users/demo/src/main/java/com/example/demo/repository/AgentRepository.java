package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
}
