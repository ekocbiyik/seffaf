package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.Order;
import com.payment.seffaf.model.OrderDetail;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ekocbiyik on 4/13/19
 */
public interface IOrderFacade {

    Order createOrder(UUID customerId, UUID addressId, String description, Map<UUID, Integer> productIdList) throws SeffafException;

    OrderDetail prepareOrder(UUID orderDetailId) throws SeffafException;

    OrderDetail transportOrder(UUID orderDetailId, String trackingNumber) throws SeffafException;

    OrderDetail deliverOrder(UUID orderId, UUID orderDetailId) throws SeffafException;

    OrderDetail approvalOrder(UUID orderDetailId, String trackingNumber) throws SeffafException;

    List<OrderDetail> cancelOrder(UUID orderId, Map<UUID, String> detailIds) throws SeffafException;

    Map getOrder(UUID orderId) throws SeffafException;

}
