package com.oracle.customer.dto;

public class CustomerResponseDTO {
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String dob;
    private String address;
    private String maskedPan;
    private String maskedAadhaar;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMaskedPan() {
		return maskedPan;
	}
	public void setMaskedPan(String maskedPan) {
		this.maskedPan = maskedPan;
	}
	public String getMaskedAadhaar() {
		return maskedAadhaar;
	}
	public void setMaskedAadhaar(String maskedAadhaar) {
		this.maskedAadhaar = maskedAadhaar;
	}

    
}
