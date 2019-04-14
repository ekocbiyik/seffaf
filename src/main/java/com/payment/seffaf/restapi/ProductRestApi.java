package com.payment.seffaf.restapi;

import com.payment.seffaf.operation.ISeffafOperationExecutor;
import com.payment.seffaf.operation.SeffafOperationRegistry;
import com.payment.seffaf.restcontroller.product.AddProductController;
import com.payment.seffaf.restcontroller.product.GetProductController;
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
public class ProductRestApi {

    static String SERVICE = "PRODUCT";
    static String addProduct = "addProduct";
    static String getProduct = "getProduct";

    static {
        SeffafOperationRegistry.register(SERVICE, addProduct, AddProductController.class);
        SeffafOperationRegistry.register(SERVICE, getProduct, GetProductController.class);
    }

    @Autowired
    private ISeffafOperationExecutor operationExecutor;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addProductApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, addProduct, body);
    }

    @RequestMapping(value = "/getProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getProductApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, getProduct, body);
    }

}
