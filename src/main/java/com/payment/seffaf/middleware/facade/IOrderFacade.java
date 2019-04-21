package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ekocbiyik on 4/13/19
 */
public interface IOrderFacade {

    Order createOrder(UUID customerId, UUID addressId, String description, Map<UUID, Integer> productIdList) throws SeffafException;

    OrderDetail prepareOrder(UUID orderDetailId, UUID sellerCustomerId) throws SeffafException;

    OrderDetail transportOrder(UUID orderDetailId, String trackingNumber, OrderStatus orderStatus) throws SeffafException;

    OrderDetail deliverOrder(UUID customerId, UUID orderId, UUID orderDetailId) throws SeffafException;

    List<OrderDetail> cancelOrder(UUID orderId, Map<UUID, String> detailIds) throws SeffafException;

    Map getOrder(UUID orderId) throws SeffafException;

    RefundedDetail getRefundedOrder(UUID refundedDetailId) throws SeffafException;

    RefundedDetail transportRefunded(UUID refundedDetailId, String trackingNumber) throws SeffafException;

    RefundedDetail deliverRefunded(UUID sellerCustomerId, UUID refundedDetailId, RefundedStatus refundedStatus, String sellerDescription) throws SeffafException;

}
