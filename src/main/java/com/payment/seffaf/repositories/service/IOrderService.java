package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Order;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
public interface IOrderService {

    void save(Order order);

    Order findOrderByOrderId(UUID orderId);

    List<Order> findAllByDeliveredCustomerId(UUID customerId);

    List<Order> getAllOrders();

}
