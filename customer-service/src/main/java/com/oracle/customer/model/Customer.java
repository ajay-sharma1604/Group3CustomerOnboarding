package com.oracle.customer.model;
import io.swagger.v3.oas.annotations.media.Schema;


// Required for mapping the class to a DB table
import jakarta.persistence.*;

// Required for field-level validations
import jakarta.validation.constraints.*;

@Entity // Marks this class as a JPA Entity (table in the database)
@Table(name = "customerservice")
public class Customer {

    @Id // Marks this field as the primary key
    @Schema(description = "Unique customer ID, auto-generated")
    private String customerId;

    @Schema(description = "Full name of the customer", example = "Ajay Sharma")
    @NotBlank(message = "Full name is mandatory") // Field must not be null or blank
    private String fullName;


    @Schema(description = "Email address", example = "ajay@example.com")
    @Email(message = "Email should be valid") // Validates email format
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Schema(description = "10-digit phone number", example = "9876543210")
    @Pattern(regexp = "^\\d{10}$", message = "Phone must be 10 digits") // Must be exactly 10 digits
    private String phone;

    @Schema(description = "Date of birth", example = "2000-01-01")
    @NotBlank(message = "DOB is required") // We are storing DOB as String (ideally should be Date)
    private String dob;

    @Schema(description = "Customer's residential address", example = "123 Street Name, City")
    @NotBlank(message = "Address is required")
    private String address;

    @Schema(description = "PAN number (e.g., ABCDE1234F)", example = "ABCDE1234F")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format") 
    // PAN: 5 uppercase letters, 4 digits, 1 uppercase letter
    private String pan;

    @Schema(description = "12-digit Aadhaar number", example = "123456789012")
    @Pattern(regexp = "\\d{12}", message = "Aadhaar must be 12 digits") // Aadhaar must be 12 digits
    private String aadhaar;

    //Default no-arg constructor required by Hibernate
    public Customer() {}

    //Parameterized constructor (used when creating an object manually)
    public Customer(String customerId,
                    @NotBlank(message = "Full name is mandatory") String fullName,
                    @Email(message = "Email should be valid") @NotBlank(message = "Email is mandatory") String email,
                    @Pattern(regexp = "^\\d{10}$", message = "Phone must be 10 digits") String phone,
                    @NotBlank(message = "DOB is required") String dob,
                    @NotBlank(message = "Address is required") String address,
                    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format") String pan,
                    @Pattern(regexp = "\\d{12}", message = "Aadhaar must be 12 digits") String aadhaar) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.pan = pan;
        this.aadhaar = aadhaar;
    }

    //Getter and Setter methods (used by Spring and JPA to access private fields)

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    //Helpful for logging/debugging
    @Override
    public String toString() {
        return "Customer [customerId=" + customerId +
               ", fullName=" + fullName +
               ", email=" + email +
               ", phone=" + phone +
               ", dob=" + dob +
               ", address=" + address +
               ", pan=" + pan +
               ", aadhaar=" + aadhaar + "]";
    }
}
