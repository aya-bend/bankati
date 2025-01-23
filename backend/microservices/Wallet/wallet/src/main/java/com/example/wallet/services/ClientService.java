package com.example.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.wallet.DTO.UserDTO;
import com.example.wallet.models.Client;
import com.example.wallet.models.TransactionType;
import com.example.wallet.repositories.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    //@Autowired
    //private TransactionService transactionService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${users.service.url}")
    private String usersServiceUrl;

    @Autowired
    private ApplicationContext applicationContext;



    // Delegate to TransactionService lazily
    private TransactionService getTransactionService() {
        return applicationContext.getBean(TransactionService.class);
    }

    // method to create new client with balance = 0
    public Client createClient(Long id) {
        Client client = new Client(id, 0.0); // Default balance = 0
        return clientRepository.save(client);
    }

    // method to retrieve client infos : balance,.. etc
    public Client getClientById(Long id) {
        // Retrieve user by username
        return clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found with id : " + id));
    }


    // method to add amount the balance + add transaction log : deposit process
    @Transactional
    public void deposit(Long userId, Double amount) {

        // Fetch the client
        Client user = clientRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Client not found with id : " + userId));

        // Update the client's balance
        user.setBalance(user.getBalance() + amount);
        clientRepository.save(user);

        // Delegate transaction creation to TransactionService
        getTransactionService().createTransaction(user, user, amount, TransactionType.DEPOSIT, "");
    }


    // method to get full name : firstname and lastname from table Clients in microservice users
    public String getUserFullName(Long userId) {
        String url = usersServiceUrl + "/users/" + userId;
        try {
            UserDTO user = restTemplate.getForObject(url, UserDTO.class);
            return user != null ? user.getFirstName() + " " + user.getLastName() : "Unknown";
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
