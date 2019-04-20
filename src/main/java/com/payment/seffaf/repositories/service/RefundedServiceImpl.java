package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.RefundedDetail;
import com.payment.seffaf.repositories.dao.IRefundedDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ekocbiyik on 4/20/19
 */
@Service
public class RefundedServiceImpl implements IRefundedService {

    @Autowired
    private IRefundedDetailDao refundedDetailDao;

    @Transactional
    @Override
    public void save(RefundedDetail refundedDetail) {
        refundedDetailDao.save(refundedDetail);
    }
}
