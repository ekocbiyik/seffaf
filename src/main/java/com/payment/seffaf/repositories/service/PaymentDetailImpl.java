package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.PaymentDetail;
import com.payment.seffaf.repositories.dao.IPaymentDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * ekocbiyik on 4/20/19
 */
@Controller
public class PaymentDetailImpl implements IPaymentDetailService {

    @Autowired
    private IPaymentDetailDao paymentDetailDao;

    @Transactional
    @Override
    public void save(PaymentDetail paymentDetail) {
        paymentDetailDao.save(paymentDetail);
    }

    @Transactional
    @Override
    public PaymentDetail getPaymentDetailById(UUID paymentDetailId) {
        return paymentDetailDao.findPaymentDetailByPaymentDetailId(paymentDetailId);
    }

    @Transactional
    @Override
    public PaymentDetail getPaymentDetailByOrderDetailId(UUID orderDetailID) {
        return paymentDetailDao.findByOrderDetailId(orderDetailID);
    }

    @Transactional
    @Override
    public List<PaymentDetail> findAllByDeliveredCustomerId(UUID customerId) {
        return paymentDetailDao.findAllByDeliveredCustomerId(customerId);
    }

    @Transactional
    @Override
    public List<PaymentDetail> findAllBySellerCustomerId(UUID sellerId) {
        return paymentDetailDao.findAllBySellerCustomerId(sellerId);
    }
}
