package com.oracle.customer.service;

import com.oracle.customer.dao.CustomerDao;
import com.oracle.customer.exception.CustomerNotFoundException;
import com.oracle.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setCustomerId("CUST" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        return customerDao.save(customer);
    }

    @Override
    public Customer findById(String customerId) {
        Customer customer = customerDao.findById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException(customerId);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer existing = customerDao.findById(customer.getCustomerId());
        if (existing == null) {
            throw new CustomerNotFoundException(customer.getCustomerId());
        }
        return customerDao.update(customer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        Customer existing = customerDao.findById(customerId);
        if (existing == null) {
            throw new CustomerNotFoundException(customerId);
        }
        customerDao.delete(customerId);
    }
}
