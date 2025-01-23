package com.example.wallet.DTO;

import lombok.Data;

@Data
public class DepositRequest {
    private Long userId;
    private Double amount;
}
