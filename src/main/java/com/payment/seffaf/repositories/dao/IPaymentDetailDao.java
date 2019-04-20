package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.PaymentDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * ekocbiyik on 4/20/19
 */
@Repository
public interface IPaymentDetailDao extends CrudRepository<PaymentDetail, UUID> {

    PaymentDetail findPaymentDetailByPaymentDetailId(UUID id);

    List<PaymentDetail> findAllByDeliveredCustomerId(UUID customerId);

    List<PaymentDetail> findAllBySellerCustomerId(UUID sellerId);

    PaymentDetail findByOrderDetailId(UUID orderDetailId);

}
