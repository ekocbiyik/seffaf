package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.middleware.facade.IAddressFacade;
import com.payment.seffaf.model.Address;
import com.payment.seffaf.repositories.service.IAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ekocbiyik on 4/10/19
 */
@Controller
public class AddressFacadeImpl implements IAddressFacade {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IAddressService addressService;

    @Override
    public Address createAddress(Address address) {
        addressService.save(address);
        logger.info("Address created with id: {}", address.getAddressId());
        return address;
    }
}
