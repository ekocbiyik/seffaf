package com.payment.seffaf.restapi;

import com.payment.seffaf.operation.ISeffafOperationExecutor;
import com.payment.seffaf.operation.SeffafOperationRegistry;
import com.payment.seffaf.restcontroller.payment.AddPaymentController;
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
@RequestMapping(value = "/api")
@RestController
public class PaymentRestApi {

    static String SERVICE = "PAYMENT";
    static String addPayment = "addPayment";

    static {
        SeffafOperationRegistry.register(SERVICE, addPayment, AddPaymentController.class);
    }

    @Autowired
    private ISeffafOperationExecutor operationExecutor;

    @RequestMapping(value = "/addPayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addPaymentApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, addPayment, body);
    }

}
