package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.exceptions.CustomerException;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.middleware.facade.IAddressFacade;
import com.payment.seffaf.model.Address;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.repositories.service.IAddressService;
import com.payment.seffaf.repositories.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

/**
 * ekocbiyik on 4/10/19
 */
@Controller
public class AddressFacadeImpl implements IAddressFacade {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ICustomerService customerService;

    @Override
    public Address createAddress(Address address) {
        addressService.save(address);
        logger.info("Address created with id: {}", address.getAddressId());
        return address;
    }

    @Override
    public List<Address> getAddressByCustomer(UUID customerId) throws SeffafException {

        Customer customerById = customerService.getCustomerById(customerId);
        if (customerById == null) {
            throw new CustomerException(SeffafExceptionCode.CUSTOMER_NOT_FOUND,
                    String.format("CUSTOMER_NOT_FOUND: %s", customerId.toString()));
        }
        logger.info("customer found: {}", customerId.toString());

        List<Address> customerAddressList = addressService.getAllByCustomerId(customerById.getCustomerId());
        logger.info("customer has {} address!", customerAddressList.size());

        return customerAddressList;
    }

}
