package com.payment.seffaf.restapi;

import com.payment.seffaf.operation.ISeffafOperationExecutor;
import com.payment.seffaf.operation.SeffafOperationRegistry;
import com.payment.seffaf.restcontroller.order.AddOrderController;
import com.payment.seffaf.restcontroller.order.CancelOrderController;
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
public class OrderRestApi {

    static String SERVICE = "ORDER";
    static String addOrder = "addOrder";
    static String cancelOrder = "cancelOrder";

    static {
        SeffafOperationRegistry.register(SERVICE, addOrder, AddOrderController.class);
        SeffafOperationRegistry.register(SERVICE, cancelOrder, CancelOrderController.class);
    }

    @Autowired
    private ISeffafOperationExecutor operationExecutor;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, addOrder, body);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map cancelOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, cancelOrder, body);
    }

}
