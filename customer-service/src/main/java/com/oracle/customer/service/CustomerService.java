package com.oracle.customer.service;

import com.oracle.customer.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer findById(String customerId);
    List<Customer> findAll();
    Customer updateCustomer(Customer customer);
    void deleteCustomer(String customerId);
}
