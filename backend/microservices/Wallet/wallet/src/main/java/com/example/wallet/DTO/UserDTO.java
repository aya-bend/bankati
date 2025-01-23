package com.example.wallet.DTO;

import com.example.wallet.models.ClientType;

import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private ClientType clientType;
    private String email;
    private String phone;
}
