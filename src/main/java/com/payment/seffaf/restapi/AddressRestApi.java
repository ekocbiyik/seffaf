package com.payment.seffaf.restapi;

import com.payment.seffaf.operation.ISeffafOperationExecutor;
import com.payment.seffaf.operation.SeffafOperationRegistry;
import com.payment.seffaf.restcontroller.address.AddAddressController;
import com.payment.seffaf.restcontroller.address.GetAddressController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ekocbiyik on 4/10/19
 */
@RequestMapping(value = "/api")
@RestController
public class AddressRestApi {

    static String SERVICE = "ADRESS";
    static String addAddress = "addAddress";
    static String getAddress = "getAddress";

    static {
        SeffafOperationRegistry.register(SERVICE, addAddress, AddAddressController.class);
        SeffafOperationRegistry.register(SERVICE, getAddress, GetAddressController.class);
    }

    @Autowired
    private ISeffafOperationExecutor operationExecutor;

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addAddressApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, addAddress, body);
    }


    @RequestMapping(value = "/getAddress", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map getAddressApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, getAddress, body);
    }


}
