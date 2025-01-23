package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Clients")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends User {
    
    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING) // Pour stocker l'Enum en format texte dans la DB
    private ClientType clientType = ClientType.HSSAB1; // Par défaut "HSSAB1"


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientType getClientType() { return clientType; }
    public void setClientType(ClientType clientType) { this.clientType = clientType; }

}