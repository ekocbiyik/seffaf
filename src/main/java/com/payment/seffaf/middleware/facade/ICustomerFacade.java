package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.model.Customer;

import java.util.Map;

/**
 * enbiya on 01.04.2019
 */
public interface ICustomerFacade {

    Customer createCustomer(Map<String, String> parameters);

}
