package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.middleware.facade.IBankAccountFacade;
import com.payment.seffaf.model.BankAccount;
import com.payment.seffaf.repositories.service.IBankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ekocbiyik on 4/10/19
 */
@Controller
public class BankAccountFacadeImpl implements IBankAccountFacade {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IBankAccountService bankAccountService;

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {

        // aynı customerId ve IbanNumber'a sahip birden çok kayıt olamaz!
        List<BankAccount> customerAccountlist = bankAccountService.getAllByCustomerId(bankAccount.getCustomerId());
        List<BankAccount> ibanNumberlist = customerAccountlist.stream().filter(a -> Objects.equals(a.getIbanNumber(), bankAccount.getIbanNumber())).collect(Collectors.toList());
        List<BankAccount> cardNumberlist = customerAccountlist.stream().filter(a -> Objects.equals(a.getCardNumber(), bankAccount.getCardNumber())).collect(Collectors.toList());

        if (!ibanNumberlist.isEmpty()) {
            logger.info("ibanNumber already exist! id: {}", ibanNumberlist.get(0).getAccountId());
            return ibanNumberlist.get(0);
        }
        if (!cardNumberlist.isEmpty()) {
            logger.info("cardNumber already exist! id: {}", cardNumberlist.get(0).getAccountId());
            return cardNumberlist.get(0);
        }

        bankAccountService.save(bankAccount);
        logger.info("BankAccount created with id: {}", bankAccount.getAccountId());
        return bankAccount;
    }
}
