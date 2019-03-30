package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 25.03.2019
 */
@Repository
public interface IAddressDao extends CrudRepository<Address, UUID> {

    Address findAddressByAddressId(UUID id);

    List<Address> findAllByCustomerId(UUID customerId);

}
