package com.example.wallet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wallet.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}