package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Customer;
import com.payment.seffaf.model.Order;
import com.payment.seffaf.model.Payment;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 26.03.2019
 */
public interface IPaymentService {

    void save(Payment payment);

    Payment findPaymentByPaymentId(UUID id);

    Payment findPaymentByOrder(Order order);

    List<Payment> findAllByDeliveredCustomer(Customer customer);

    List<Payment> findAllBySellerCustomer(Customer customer);

    List<Payment> getAllPayments();
}
