package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Payment;
import com.payment.seffaf.repositories.dao.IPaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 26.03.2019
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private IPaymentDao paymentDao;

    @Transactional
    @Override
    public void save(Payment payment) {
        paymentDao.save(payment);
    }

    @Transactional
    @Override
    public Payment findPaymentByPaymentId(UUID id) {
        return paymentDao.findPaymentByPaymentId(id);
    }

    @Transactional
    @Override
    public Payment findPaymentByOrderId(UUID orderId) {
        return paymentDao.findPaymentByOrderId(orderId);
    }

    @Transactional
    @Override
    public List<Payment> findAllByDeliveredCustomerId(UUID customerId) {
        return paymentDao.findAllByDeliveredCustomerId(customerId);
    }

    @Transactional
    @Override
    public List<Payment> getAllPayments() {
        return (List<Payment>) paymentDao.findAll();
    }

}
