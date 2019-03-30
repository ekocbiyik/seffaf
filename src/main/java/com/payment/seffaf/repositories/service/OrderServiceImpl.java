package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Order;
import com.payment.seffaf.repositories.dao.IOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Transactional
    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Transactional
    @Override
    public Order findOrderByOrderId(UUID orderId) {
        return orderDao.findOrderByOrderId(orderId);
    }

    @Transactional
    @Override
    public List<Order> findAllByDeliveredCustomerId(UUID customerId) {
        return orderDao.findAllByDeliveredCustomerId(customerId);
    }

    @Transactional
    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderDao.findAll();
    }
}
