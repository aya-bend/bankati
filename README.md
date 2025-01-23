# E-Wallet Application

## Overview

The **Bankati Application** is a team project designed to simulate a digital wallet system where users can perform various operations such as deposits, transactions, and account management. This project is built using microservices architecture and incorporates a frontend in Angular for a seamless user experience.

---

## Features

### Backend (Microservices):
1. **Authentication Service**:
   - Manages user authentication with JWT (JSON Web Token).
   - Handles login functionality.
   - Ensures secure password encoding using BCrypt.

2. **Users Service**:
   - Manages user profiles (Admin, Agent, Client).
   - Facilitates the registration request system for agents and clients.
   - Provides profile details for users based on their role.

3. **Wallet Service**:
   - Manages client balances and transactions.
   - Records transaction details such as sender, receiver, amount, type, and date.
   - Updates balances dynamically based on deposits and other transactions.

4. **Integration Between Microservices**:
   - Authentication service validates and generates tokens.
   - Users and Wallet services communicate seamlessly for profile and transaction operations.

### Frontend:
- Built using Angular.
- Fully responsive UI with a modern and clean design.
- Functionalities include:
  - Profile pages for different user roles (Admin, Agent, Client).
  - Wallet interface for deposits and transaction history.
  - Connection between backend APIs and frontend components.

---

## Architecture

This project follows a microservices architecture where each service is responsible for a specific domain:

- **Authentication Service**: Handles authentication and JWT token management.
- **Users Service**: Manages user data and profile details.
- **Wallet Service**: Manages transactions and client balances.

### Technology Stack

- **Backend**: Spring Boot, JPA, Hibernate, RestTemplate
- **Frontend**: Angular
- **Database**: MySQL
- **Security**: Spring Security with JWT Authentication
- **Build Tools**: Maven, npm
- **Other Tools**: Postman (API Testing)

---

## Contributions

### My Work:
- Implemented the following microservices:
  1. **Authentication Service**:
     - JWT authentication and login functionality.
     - Secure password handling with BCrypt.
  2. **Users Service**:
     - Registration request system for agents and clients.
     - User profile management APIs.
  3. **Wallet Service**:
     - Transaction management.
     - Dynamic balance updates.
     - Inter-microservice communication for user information.

- Made significant modifications to the frontend:
  - Connected Angular components to backend APIs.
  - Added features such as:
    - Profile display for Admin, Agent, and Client roles.
    - Deposit functionality with live updates on balances and transaction history.

### Team Contributions:
- The frontend was initially developed by a teammate, and I collaborated to enhance the backend integration.

---

## Video Demonstration

A detailed walkthrough of the application is available in this video: **[Watch the Demo](demo.mp4)** Alternatively, you can access the video directly on Google Drive using the following link: https://drive.google.com/file/d/1Y-cXaYIq41J8U6iMKgckeHOwa5ukDZdZ/view?usp=sharing
The video highlights:
- Microservices functionality.
- Backend-frontend integration.
- How Bankati works for Admin, Agent, and Client roles.

---

## How to Run

### Prerequisites
1. Java 17+ installed.
2. Node.js and npm installed.
3. MySQL for database setup.

### Backend
1. Clone the repositories for **authentication**, **users**, and **wallet** microservices.
2. Navigate to each service folder and run:
   ```bash
   mvn spring-boot:run
3. Ensure the database is running on MySQL.