package com.oracle.kyc.dto;

public class AccountRequestDTO {
    private String customerId;
    private String accountType = "SAVINGS"; // default

    public AccountRequestDTO() {}

    public AccountRequestDTO(String customerId) {
        this.customerId = customerId;
        this.accountType = "SAVINGS";
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
