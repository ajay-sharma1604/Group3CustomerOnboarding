package com.oracle.customer.dao;

import com.oracle.customer.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer save(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public Customer findById(String customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public void delete(String customerId) {
        Customer customer = findById(customerId);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }
}
