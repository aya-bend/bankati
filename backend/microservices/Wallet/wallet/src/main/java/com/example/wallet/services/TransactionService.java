package com.example.wallet.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.DTO.TransactionDTO;
import com.example.wallet.models.Client;
import com.example.wallet.models.Transaction;
import com.example.wallet.models.TransactionType;
import com.example.wallet.repositories.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientService clientService;

    // service to create a new transaction
    public void createTransaction(Client sender, Client receiver, Double amount, TransactionType type, String description) {
        // Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setDescription(description);
        transactionRepository.save(transaction);
    }


    public List<TransactionDTO> getTransactionsByUserId(Long userId) {
        // Fetch transactions
        List<Transaction> transactions = transactionRepository.findBySenderIdOrReceiverId(userId, userId);

        // Map transactions to TransactionDTO
        return transactions.stream().map(transaction -> {
            TransactionDTO dto = new TransactionDTO();
            dto.setId(transaction.getId());
            dto.setAmount(transaction.getAmount());
            dto.setDate(transaction.getDate());
            dto.setType(transaction.getType());
            dto.setDescription(transaction.getDescription());

            // Fetch names using UserClientService
            dto.setSenderName(clientService.getUserFullName(transaction.getSender().getId()));
            dto.setReceiverName(clientService.getUserFullName(transaction.getReceiver().getId()));

            return dto;
        }).collect(Collectors.toList());
    }
    
}
