package com.oracle.kyc.dto;

import lombok.Data;

@Data
public class AccountResponseDTO {
    private String message;
    private String accountNumber;
    private String accountStatus;

    public AccountResponseDTO() {}  // Required for deserialization

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
    
    
}
