package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.Order;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ekocbiyik on 4/13/19
 */
public interface IOrderFacade {

    Order createOrder(UUID customerId, UUID addressId, String description, Map<UUID, Integer> productIdList) throws SeffafException;

}
