package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.OrderDetail;
import com.payment.seffaf.repositories.dao.IOrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    private IOrderDetailDao orderDetailDao;

    @Transactional
    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailDao.save(orderDetail);
    }

    @Transactional
    @Override
    public OrderDetail getOrderDetailById(UUID id) {
        return orderDetailDao.findOrderDetailByOrderDetailId(id);
    }

    @Transactional
    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(UUID orderID) {
        return orderDetailDao.findAllByOrderId(orderID);
    }

    @Transactional
    @Override
    public List<OrderDetail> findAllByDeliveredCustomerId(UUID customerId) {
        return orderDetailDao.findAllByDeliveredCustomerId(customerId);
    }

    @Transactional
    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return (List<OrderDetail>) orderDetailDao.findAll();
    }
}
