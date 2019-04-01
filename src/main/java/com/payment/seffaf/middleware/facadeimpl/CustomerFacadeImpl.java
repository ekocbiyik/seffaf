package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.middleware.facade.ICustomerFacade;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.model.Gender;
import com.payment.seffaf.repositories.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * enbiya on 01.04.2019
 */
@Controller
public class CustomerFacadeImpl implements ICustomerFacade {

    @Autowired
    private ICustomerService customerService;

    @Override
    public Customer createCustomer(Map<String, String> parameters) {

        Customer c = new Customer();
        c.setName(parameters.get("name"));
        c.setSurname(parameters.get("surname"));
        c.setGender(Gender.valueOf(parameters.get("gender")));
        c.setPhoneNumber(parameters.get("phoneNumber"));
        c.setEmail(parameters.get("email"));
        c.setActive(true);

        customerService.save(c);
        return c;
    }
}
