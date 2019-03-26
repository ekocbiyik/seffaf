package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.Customer;
import com.payment.seffaf.model.Order;
import com.payment.seffaf.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 26.03.2019
 */
@Repository
public interface IPaymentDao extends CrudRepository<Payment, UUID> {

    Payment findPaymentByPaymentId(UUID id);

    Payment findPaymentByOrder(Order order);

    List<Payment> findAllByDeliveredCustomer(Customer customer);

    List<Payment> findAllBySellerCustomer(Customer customer);

}
