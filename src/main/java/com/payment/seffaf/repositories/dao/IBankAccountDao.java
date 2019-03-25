package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.BankAccount;
import com.payment.seffaf.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 25.03.2019
 */
@Repository
public interface IBankAccountDao extends CrudRepository<BankAccount, UUID> {

    BankAccount findBankAccountByAccountId(UUID id);

    List<BankAccount> findAllByCustomer(Customer customer);

}
