package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.PaymentDetail;

import java.util.List;
import java.util.UUID;

/**
 * ekocbiyik on 4/20/19
 */
public interface IPaymentDetailService {

    void save(PaymentDetail paymentDetail);

    PaymentDetail getPaymentDetailById(UUID id);

    PaymentDetail getPaymentDetailByOrderDetailId(UUID orderDetailID);

    List<PaymentDetail> findAllByDeliveredCustomerId(UUID customerId);

    List<PaymentDetail> findAllBySellerCustomerId(UUID customerId);

}
