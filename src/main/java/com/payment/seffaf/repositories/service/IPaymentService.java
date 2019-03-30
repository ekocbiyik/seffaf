package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Payment;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 26.03.2019
 */
public interface IPaymentService {

    void save(Payment payment);

    Payment findPaymentByPaymentId(UUID id);

    Payment findPaymentByOrderId(UUID orderId);

    List<Payment> findAllByDeliveredCustomerId(UUID customerId);

    List<Payment> findAllBySellerCustomerId(UUID customerId);

    List<Payment> getAllPayments();
}
