package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Customer;
import com.payment.seffaf.repositories.dao.ICustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Transactional
    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Transactional
    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Transactional
    @Override
    public Customer getCustomerById(UUID id) {
        return customerDao.findByCustomerId(id);
    }

    @Transactional
    @Override
    public List<Customer> getAllCustomer() {
        return (List<Customer>) customerDao.findAll();
    }

    @Transactional
    @Override
    public List<Customer> findAllByEmailOrPhoneNumber(String email, String phoneNumber) {
        return customerDao.findAllByEmailOrPhoneNumber(email, phoneNumber);
    }
}
