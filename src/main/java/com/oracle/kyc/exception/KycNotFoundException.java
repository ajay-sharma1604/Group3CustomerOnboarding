package com.oracle.kyc.exception;

public class KycNotFoundException extends RuntimeException {

    public KycNotFoundException(String customerId) {
        super("KYC details not found for customer ID: " + customerId);
    }
}
