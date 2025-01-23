package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "registration_requests_clients")
public class RegistrationRequestClient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING) // Pour stocker l'Enum en format texte dans la DB
    private ClientType clientType = ClientType.HSSAB1; // Par défaut "HSSAB1"
    @Enumerated(EnumType.STRING) // Pour stocker l'Enum en format texte dans la DB
    private RegistrationStatus status = RegistrationStatus.PENDING; // Par défaut "PENDING"


    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public ClientType getClientType() { return clientType; }
    public void setClientType(ClientType clientType) { this.clientType = clientType; }

    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }
}
