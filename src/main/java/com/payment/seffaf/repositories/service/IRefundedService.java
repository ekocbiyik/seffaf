package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.RefundedDetail;

import java.util.UUID;

/**
 * ekocbiyik on 4/20/19
 */
public interface IRefundedService {

    void save(RefundedDetail refundedDetail);

    RefundedDetail getRefundedById(UUID refundedDetailId);

}
