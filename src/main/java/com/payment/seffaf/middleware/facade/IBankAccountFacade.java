package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.BankAccount;

import java.util.UUID;

/**
 * ekocbiyik on 4/10/19
 */
public interface IBankAccountFacade {

    BankAccount createBankAccount(BankAccount bankAccount, UUID productId) throws SeffafException;

}
