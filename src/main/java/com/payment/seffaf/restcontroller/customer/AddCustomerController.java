package com.payment.seffaf.restcontroller.customer;

import com.payment.seffaf.middleware.facade.ICustomerFacade;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.operation.SeffafOperationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class AddCustomerController extends SeffafOperationImpl {

    @Autowired
    private ICustomerFacade customerFacade;

    private String request;
    private Map<String, String> parameters;

    @Override
    public Object init(Object... params) {
        logger.info("params: {}", params);
        this.request = params[0].toString();
        return request;
    }

    @Override
    public void validate() {
        logger.info("request: {}", request);
        parameters = new HashMap<>();
        parameters.put("name", "enbiya");
    }

    @Override
    public Object operate() {
        logger.info("AddCustomerController operate executed!");
        Customer customer = customerFacade.createCustomer(parameters);
        Map<String, Object> resutlMap = new HashMap();
        resutlMap.put("customer", customer);
        return resutlMap;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddCustomerController handleException executed!");
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("responseCode", "100");
        responseMap.put("message", e.getMessage());
        return responseMap;
    }
}
