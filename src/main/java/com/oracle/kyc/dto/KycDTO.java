package com.oracle.kyc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "KYC Data Transfer Object")
public class KycDTO {

    @NotBlank(message = "Customer ID is required")
    @Schema(description = "Unique ID of the customer", example = "CUST123")
    private String customerId;

    @NotBlank(message = "PAN is required")
    @Size(min = 10, max = 10, message = "PAN must be 10 characters")
    @Schema(description = "PAN number", example = "ABCDE1234F")
    private String panNumber;

    @NotBlank(message = "Aadhaar is required")
    @Size(min = 12, max = 12, message = "Aadhaar must be 12 digits")
    @Schema(description = "Aadhaar number", example = "123456789012")
    private String aadhaarNumber;

    @NotBlank(message = "Photograph is required")
    @Schema(description = "Photograph as base64 string or metadata", example = "data:image/jpeg;base64,...")
    private String photograph;

   
    private String documentType;
    private String documentUrl;

    private String maskedPan;
    private String maskedAadhaar;

    private String kycStatus;    // Added
    private String adminRemark;  // Added

    public KycDTO() {}

    public KycDTO(String customerId, String panNumber, String aadhaarNumber, String photograph,
                  String maskedPan, String maskedAadhaar, String kycStatus, String adminRemark) {
        this.customerId = customerId;
        this.panNumber = panNumber;
        this.aadhaarNumber = aadhaarNumber;
        this.photograph = photograph;
        this.maskedPan = maskedPan;
        this.maskedAadhaar = maskedAadhaar;
        this.kycStatus = kycStatus;
        this.adminRemark = adminRemark;
    }

    // Getters & Setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public String getAadhaarNumber() { return aadhaarNumber; }
    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public String getPhotograph() { return photograph; }
    public void setPhotograph(String photograph) { this.photograph = photograph; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getDocumentUrl() { return documentUrl; }
    public void setDocumentUrl(String documentUrl) { this.documentUrl = documentUrl; }

    public String getMaskedPan() { return maskedPan; }
    public void setMaskedPan(String maskedPan) { this.maskedPan = maskedPan; }

    public String getMaskedAadhaar() { return maskedAadhaar; }
    public void setMaskedAadhaar(String maskedAadhaar) { this.maskedAadhaar = maskedAadhaar; }

    public String getKycStatus() { return kycStatus; }
    public void setKycStatus(String kycStatus) { this.kycStatus = kycStatus; }

    public String getAdminRemark() { return adminRemark; }
    public void setAdminRemark(String adminRemark) { this.adminRemark = adminRemark; }
}
