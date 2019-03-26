package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.BankAccount;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.repositories.dao.IBankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 25.03.2019
 */
@Service
public class BankAccountServiceImpl implements IBankAccountService {

    @Autowired
    private IBankAccountDao accountDao;

    @Transactional
    @Override
    public void save(BankAccount account) {
        accountDao.save(account);
    }

    @Transactional
    @Override
    public void delete(BankAccount account) {
        accountDao.delete(account);
    }

    @Transactional
    @Override
    public BankAccount getBankAccountById(UUID id) {
        return accountDao.findBankAccountByAccountId(id);
    }

    @Transactional
    @Override
    public List<BankAccount> getAllByCustomer(Customer customer) {
        return accountDao.findAllByCustomer(customer);
    }

    @Transactional
    @Override
    public List<BankAccount> getAllBankAccounts() {
        return (List<BankAccount>) accountDao.findAll();
    }
}
