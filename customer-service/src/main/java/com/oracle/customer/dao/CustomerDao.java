package com.oracle.customer.dao;

import com.oracle.customer.model.Customer;
import java.util.List;

public interface CustomerDao {
    Customer save(Customer customer);
    Customer findById(String customerId);
    List<Customer> findAll();
    Customer update(Customer customer);
    void delete(String customerId);
}
