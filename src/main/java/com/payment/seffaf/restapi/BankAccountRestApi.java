package com.payment.seffaf.restapi;

import com.payment.seffaf.operation.ISeffafOperationExecutor;
import com.payment.seffaf.operation.SeffafOperationRegistry;
import com.payment.seffaf.restcontroller.account.AddBankAccountController;
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
public class BankAccountRestApi {

    static String SERVICE = "BANK_ACCOUNT";
    static String addBankAccount = "addBankAccount";

    static {
        SeffafOperationRegistry.register(SERVICE, addBankAccount, AddBankAccountController.class);
    }

    @Autowired
    private ISeffafOperationExecutor operationExecutor;

    @RequestMapping(value = "/addBankAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map addBankAccountApi(@RequestBody Map body) {
        return (Map) operationExecutor.execute(SERVICE, addBankAccount, body);
    }

}
