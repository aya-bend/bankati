package com.example.demo.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Agents")
@Inheritance(strategy = InheritanceType.JOINED)

public class Agent extends User {

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    private String idType;

    private String idNumber;

    private LocalDate birthDate;

    private String address;

    private String immatriculation;

    private String patentNumber;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }
}
