package com.payment.seffaf.restcontroller.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.exceptions.ValidationException;
import com.payment.seffaf.middleware.facade.IBankAccountFacade;
import com.payment.seffaf.model.BankAccount;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.UUID;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class AddBankAccountController extends SeffafOperationImpl {

    @Autowired
    private IBankAccountFacade bankAccountFacade;

    private Map request;
    private BankAccount bankAccount;
    private UUID productId;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws ValidationException {
        ValidationUtils.UUIDValidation(request.get("customerId").toString());
        ValidationUtils.UUIDValidation(request.get("productId").toString());
        ValidationUtils.textValidation(request.get("cardHolderName").toString());
        ValidationUtils.IBANValidation(request.get("ibanNumber").toString());

        //optional
        if (request.containsKey("cardNumber")) {
            ValidationUtils.creditCardValidation(request.get("cardNumber").toString());
        }

        bankAccount = new BankAccount();
        bankAccount.setCustomerId(UUID.fromString(request.get("customerId").toString()));
        bankAccount.setCardHolderName(request.get("cardHolderName").toString());
        bankAccount.setIbanNumber(request.get("ibanNumber").toString());

        productId = UUID.fromString(request.get("productId").toString());
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("AddBankAccountController operate executed!");
        bankAccount = bankAccountFacade.createBankAccount(bankAccount, productId);

        ObjectMapper oMapper = new ObjectMapper();
        AddBankAccountOutput output = new AddBankAccountOutput(100, bankAccount);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddBankAccountController handleException executed!");
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(99, e.getMessage()),
                        Map.class);
    }
}
