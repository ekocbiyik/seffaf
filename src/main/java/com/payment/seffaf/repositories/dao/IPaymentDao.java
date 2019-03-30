package com.payment.seffaf.repositories.dao;

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

    Payment findPaymentByOrderId(UUID orderId);

    List<Payment> findAllByDeliveredCustomerId(UUID customerId);

    List<Payment> findAllBySellerCustomerId(UUID customerId);

}
