package com.oracle.customer.controller;

import com.oracle.customer.dto.CustomerResponseDTO;
import com.oracle.customer.model.Customer;
import com.oracle.customer.service.CustomerService;
import com.oracle.customer.util.CustomerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customerservice/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Register new customer
    @Operation(summary = "Register a new customer", description = "Creates a new customer with basic info and auto-generates ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Customer registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/customers")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Get all customers (masked)
    @Operation(summary = "Fetch all registered customers", description = "Returns list of all customers")
    @ApiResponse(responseCode = "200", description = "Fetched all customers successfully")
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        List<CustomerResponseDTO> dtos = customers.stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Get customer by ID (masked)
    @Operation(summary = "Get customer by ID", description = "Returns customer details for the given ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer found"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable String customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer != null) {
            CustomerResponseDTO dto = CustomerMapper.toDto(customer);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update customer
    @Operation(summary = "Update customer", description = "Updates customer information based on ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer updated"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId,
                                                   @Valid @RequestBody Customer updatedCustomer) {
        Customer existing = customerService.findById(customerId);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedCustomer.setCustomerId(customerId);
        Customer saved = customerService.updateCustomer(updatedCustomer);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    // Delete customer
    @Operation(summary = "Delete customer", description = "Deletes a customer by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Customer deleted"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        Customer existing = customerService.findById(customerId);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
