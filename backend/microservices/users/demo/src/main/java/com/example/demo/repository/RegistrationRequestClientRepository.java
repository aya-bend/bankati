package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.RegistrationRequestClient;
import com.example.demo.models.RegistrationStatus;

@Repository
public interface RegistrationRequestClientRepository extends JpaRepository<RegistrationRequestClient,Long> {
    List<RegistrationRequestClient> findByStatus(RegistrationStatus status);
}
