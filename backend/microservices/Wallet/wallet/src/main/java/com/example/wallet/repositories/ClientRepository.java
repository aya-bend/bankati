package com.example.wallet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wallet.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}