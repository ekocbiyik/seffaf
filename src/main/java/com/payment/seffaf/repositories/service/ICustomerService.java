package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Customer;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
public interface ICustomerService {

    void save(Customer customer);

    void delete(Customer customer);

    Customer getCustomerById(UUID id);

    List<Customer> getAllCustomer();

}
