package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.RefundedDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * ekocbiyik on 4/20/19
 */
@Repository
public interface IRefundedDetailDao extends CrudRepository<RefundedDetail, UUID> {

    RefundedDetail findByRefundedDetailId(UUID refundedDetailId);

}
