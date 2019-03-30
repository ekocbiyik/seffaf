package com.payment.seffaf.restapi;

import com.payment.seffaf.controller.customer.AddCustomerController;
import com.payment.seffaf.operation.ISeffafOperationExecutor;
import com.payment.seffaf.operation.SeffafOperationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * enbiya on 30.03.2019
 */
@RestController
public class CustomerRestController {

    static String SERVICE = "CUSTOMER";
    static String addCustomer = "addCustomer";

    static {
        SeffafOperationRegistry.register(SERVICE, addCustomer, AddCustomerController.class);
    }

    @Autowired
    private ISeffafOperationExecutor operationExecutor;

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addCustomerApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, addCustomer, body);
    }

}
