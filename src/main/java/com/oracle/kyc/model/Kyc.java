package com.oracle.kyc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kycDetails")
public class Kyc {

    @Id
    private String customerId;

    private String panNumber;
    private String aadhaarNumber;
    private String documentType;
    private String documentUrl;
    
    private String photograph;	//base64 or image metadata
    private String kycStatus;	//Pending, Verified, Rejected
    private String adminRemark;	//Optional message by admin
    
    //Getters and Setters
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentUrl() {
		return documentUrl;
	}
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
	public String getPhotograph() {
		return photograph;
	}
	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}
	public String getKycStatus() {
		return kycStatus;
	}
	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}
	public String getAdminRemark() {
		return adminRemark;
	}
	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}
	
	
}
