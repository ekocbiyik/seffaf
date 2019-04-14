package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
@Repository
public interface IOrderDetailDao extends CrudRepository<OrderDetail, UUID> {

    OrderDetail findOrderDetailByOrderDetailId(UUID id);

    List<OrderDetail> findAllByDeliveredCustomerId(UUID customerId);

    List<OrderDetail> findAllByOrderId(UUID orderId);

}
