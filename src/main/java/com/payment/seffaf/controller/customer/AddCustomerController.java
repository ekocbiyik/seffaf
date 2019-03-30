package com.payment.seffaf.controller.customer;

import com.payment.seffaf.model.Customer;
import com.payment.seffaf.operation.SeffafOperationImpl;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class AddCustomerController extends SeffafOperationImpl {

    @Override
    public void init(Object... params) {
        logger.info("AddCustomerController init executed!");
    }

    @Override
    public void validate() {
        logger.info("AddCustomerController validate executed!");
    }

    @Override
    public Object operate() {
        logger.info("AddCustomerController operate executed!");
        Customer c = new Customer();
        c.setName("Enbiya");
        c.setSurname("KOÇBIYIK");

        Map<String, Object> resutlMap = new HashMap();
        resutlMap.put("customer", c);
        return resutlMap;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddCustomerController handleException executed!");
        return null;
    }
}
