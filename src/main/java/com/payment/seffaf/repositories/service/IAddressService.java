package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Address;
import com.payment.seffaf.model.Customer;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 25.03.2019
 */
public interface IAddressService {

    void save(Address address);

    void delete(Address address);

    Address getAddressById(UUID id);

    List<Address> getAllByCustomerId(UUID customerId);

    List<Address> getAllAddresses();

}
