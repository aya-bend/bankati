package com.example.wallet.DTO;

import java.time.LocalDateTime;

import com.example.wallet.models.TransactionType;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long id;
    private String senderName;
    private String receiverName;
    private TransactionType type;
    private String description;
    private double amount;
    private LocalDateTime date;
}
