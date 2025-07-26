package com.oracle.service;

import com.oracle.dto.AccountRequest;
import com.oracle.dto.AccountResponse;
import com.oracle.model.Account;
import com.oracle.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Check if KYC status is VERIFIED
    private boolean isKycVerified(String customerId) {
        String url = "http://localhost:8082/kycservice/api/kyc/" + customerId + "/status";

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            String status = (String) response.getBody().get("kycStatus");
            return "VERIFIED".equalsIgnoreCase(status);
        } catch (Exception e) {
            return false;
        }
    }

    // Auto-create account after KYC is verified
    public AccountResponse createAccount(AccountRequest request) {
        AccountResponse response = new AccountResponse();

        if (!isKycVerified(request.getCustomerId())) {
            response.setMessage("Account creation denied: KYC not verified.");
            response.setAccountNumber("N/A");
            response.setAccountStatus("REJECTED");
            return response;
        }

        Optional<Account> existing = accountRepository.findByCustomerId(request.getCustomerId());
        if (existing.isPresent()) {
            Account acc = existing.get();
            response.setMessage("Account already exists.");
            response.setAccountNumber(acc.getAccountNumber());
            response.setAccountStatus(acc.getAccountStatus());
            return response;
        }

        Account account = new Account();
        account.setCustomerId(request.getCustomerId());
        account.setAccountType(request.getAccountType());
        account.setAccountNumber(generateAccountNumber());
        account.setAccountStatus("ACTIVE");
        account.setCreatedOn(LocalDateTime.now());

        accountRepository.save(account);

        response.setMessage("Account created successfully.");
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountStatus(account.getAccountStatus());

        return response;
    }

    // Generate random account number
    private String generateAccountNumber() {
        int number = (int) (Math.random() * 900000) + 100000;
        return "AC" + number;
    }

    // GET: Retrieve account by customer ID
    public AccountResponse getAccountByCustomerId(String customerId) {
        AccountResponse response = new AccountResponse();
        try {
            Optional<Account> optionalAccount = accountRepository.findByCustomerId(customerId);

            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
                response.setAccountNumber(account.getAccountNumber());
                response.setAccountStatus(account.getAccountStatus());
                response.setMessage("Account found for customer ID.");
            } else {
                response.setMessage("No account found for customer ID.");
                response.setAccountNumber("N/A");
                response.setAccountStatus("NOT_FOUND");
            }
        } catch (Exception e) {
            response.setMessage("Error: " + e.getMessage());
            response.setAccountNumber("ERROR");
            response.setAccountStatus("ERROR");
            e.printStackTrace();  // This will show the exact cause in the console
        }

        return response;
    }

}
