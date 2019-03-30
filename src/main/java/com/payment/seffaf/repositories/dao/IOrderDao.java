package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
@Repository
public interface IOrderDao extends CrudRepository<Order, UUID> {

    Order findOrderByOrderId(UUID orderId);

    List<Order> findAllByDeliveredCustomerId(UUID customerId);

}
