package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Repository
public interface ICustomerDao extends CrudRepository<Customer, UUID> {

    Customer findByCustomerId(UUID id);

}
