package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.Address;

import java.util.List;
import java.util.UUID;

/**
 * ekocbiyik on 4/10/19
 */
public interface IAddressFacade {

    Address createAddress(Address address);

    List<Address> getAddressByCustomer(UUID customerId) throws SeffafException;

}
