package com.payment.seffaf.restcontroller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.middleware.facade.IOrderFacade;
import com.payment.seffaf.model.Order;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class AddOrderController extends SeffafOperationImpl {

    private Map request;
    private UUID customerId;
    private UUID addressId;
    private String description;
    private Map<UUID, Integer> productList = new HashMap<>();

    @Autowired
    private IOrderFacade orderFacade;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws SeffafException {
        ValidationUtils.UUIDValidation(request.get("customerId").toString());
        ValidationUtils.UUIDValidation(request.get("addressId").toString());

        customerId = UUID.fromString(request.get("customerId").toString());
        addressId = UUID.fromString(request.get("addressId").toString());

        if (request.containsKey("description")) {
            description = String.valueOf(request.get("description"));
        }

        for (Object pList : ((ArrayList) request.get("products"))) {
            String productId = ((LinkedHashMap) pList).get("productId").toString();
            String productCount = ((LinkedHashMap) pList).get("productCount").toString();
            ValidationUtils.UUIDValidation(productId);
            ValidationUtils.numberValidation(productCount);
            if (Integer.parseInt(productCount) < 1) {
                throw new SeffafException(SeffafExceptionCode.PRODUCT_COUNT_MUST_BE_DIFFERENT_ZERO, "PRODUCT_COUNT_MUST_BE_DIFFERENT_ZERO");
            }
            productList.put(UUID.fromString(productId), Integer.parseInt(productCount));
        }
        if (productList.isEmpty()) {
            throw new SeffafException(SeffafExceptionCode.PRODUCT_LIST_CAN_NOT_BE_EMPTY, "PRODUCT_LIST_CAN_NOT_BE_EMPTY");
        }
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("AddOrderController operate executed!");
        Order order = orderFacade.createOrder(customerId, addressId, description, productList);

        ObjectMapper oMapper = new ObjectMapper();
        AddOrderOutput output = new AddOrderOutput(100, order);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddOrderController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
