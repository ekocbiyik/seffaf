package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.BankAccount;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 25.03.2019
 */
public interface IBankAccountService {

    void save(BankAccount account);

    void delete(BankAccount account);

    BankAccount getBankAccountById(UUID id);

    List<BankAccount> getAllByCustomerId(UUID customerId);

    List<BankAccount> getAllBankAccounts();

}
