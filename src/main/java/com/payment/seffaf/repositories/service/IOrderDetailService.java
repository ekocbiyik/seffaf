package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.OrderDetail;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
public interface IOrderDetailService {

    void save(OrderDetail orderDetail);

    OrderDetail getOrderDetailById(UUID id);

    List<OrderDetail> getOrderDetailsByOrderId(UUID orderID);

    List<OrderDetail> findAllByDeliveredCustomerId(UUID customerId);

    List<OrderDetail> getAllOrderDetails();

}
