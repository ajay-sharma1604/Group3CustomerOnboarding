package com.oracle.customer.util;

import com.oracle.customer.dto.CustomerResponseDTO;
import com.oracle.customer.model.Customer;

public class CustomerMapper {

    public static CustomerResponseDTO toDto(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getCustomerId());
        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setDob(customer.getDob());
        dto.setAddress(customer.getAddress());

        // Mask PAN: show only last 4 digits
        String pan = customer.getPan();
        dto.setMaskedPan(pan.replaceAll(".(?=.{4})", "*"));

        // Mask Aadhaar: show only last 4 digits
        String aadhaar = customer.getAadhaar();
        dto.setMaskedAadhaar(aadhaar.replaceAll(".(?=.{4})", "*"));

        return dto;
    }
}
