package com.oracle.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oracle.dto.AccountRequest;
import com.oracle.dto.AccountResponse;
import com.oracle.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Operation(summary = "Create account for verified customer")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get account by customer ID (auto-create if KYC is verified)")
    public ResponseEntity<AccountResponse> getAccountByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(accountService.getAccountByCustomerId(customerId));
    }
}
