package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.middleware.facade.ICustomerFacade;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.repositories.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * enbiya on 01.04.2019
 */
@Controller
public class CustomerFacadeImpl implements ICustomerFacade {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ICustomerService customerService;

    @Override
    public Customer createCustomer(Customer customer) {

        List<Customer> customerList = customerService.findAllByEmailOrPhoneNumber(customer.getEmail(), customer.getPhoneNumber());

        logger.info("email: {} or phoneNumber: {} result size: {}", customer.getEmail(), customer.getPhoneNumber(), customerList.size());
        if (customerList.size() > 0) {
            logger.info("Customer already exist!");
            return customerList.get(0);
        }

        customer.setActive(true);
        customerService.save(customer);
        logger.info("Customer created with id: {}", customer.getCustomerId());
        return customer;
    }
}
