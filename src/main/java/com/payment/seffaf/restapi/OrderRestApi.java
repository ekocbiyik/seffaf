package com.payment.seffaf.restapi;

import com.payment.seffaf.operation.ISeffafOperationExecutor;
import com.payment.seffaf.operation.SeffafOperationRegistry;
import com.payment.seffaf.restcontroller.order.*;
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
    static String getOrder = "getOrder";
    static String cancelOrder = "cancelOrder";
    static String prepareOrder = "prepareOrder";
    static String transportOrder = "transportOrder";
    static String approvalOrder = "approvalOrder";
    static String deliverOrder = "delivertOrder";

    static {
        SeffafOperationRegistry.register(SERVICE, addOrder, AddOrderController.class);
        SeffafOperationRegistry.register(SERVICE, getOrder, GetOrderController.class);
        SeffafOperationRegistry.register(SERVICE, cancelOrder, CancelOrderController.class);
        SeffafOperationRegistry.register(SERVICE, prepareOrder, PrepareOrderController.class);
        SeffafOperationRegistry.register(SERVICE, transportOrder, TransportOrderController.class);
        SeffafOperationRegistry.register(SERVICE, approvalOrder, ApprovalOrderController.class);
        SeffafOperationRegistry.register(SERVICE, deliverOrder, DeliverOrderController.class);
    }

    @Autowired
    private ISeffafOperationExecutor operationExecutor;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, addOrder, body);
    }

    @RequestMapping(value = "/getOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, getOrder, body);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map cancelOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, cancelOrder, body);
    }

    @RequestMapping(value = "/prepareOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map prepareOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, prepareOrder, body);
    }

    @RequestMapping(value = "/transportOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map transportOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, transportOrder, body);
    }

    @RequestMapping(value = "/approvalOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map approvalOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, approvalOrder, body);
    }

    @RequestMapping(value = "/deliverOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map deliverOrderApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, deliverOrder, body);
    }


}
