package com.payment.seffaf.restcontroller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.middleware.facade.IOrderFacade;
import com.payment.seffaf.model.OrderDetail;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * ekocbiyik on 4/14/19
 */
@Controller
public class CancelOrderController extends SeffafOperationImpl {

    private Map request;
    private UUID orderId;
    private Map<UUID, String> orderDetails;

    @Autowired
    private IOrderFacade orderFacade;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws SeffafException {
        ValidationUtils.UUIDValidation(request.get("orderId").toString());
        orderId = UUID.fromString(request.get("orderId").toString());

        orderDetails = new HashMap<>();
        for (Object oList : ((ArrayList) request.get("orderDetails"))) {
            String orderDetailId = ((LinkedHashMap) oList).get("orderDetailId").toString();
            String customerDescription = ((LinkedHashMap) oList).get("customerDescription").toString();

            ValidationUtils.UUIDValidation(orderDetailId);
            ValidationUtils.notEmptyStringValidation(customerDescription); // çok önemli!
            orderDetails.put(UUID.fromString(orderDetailId), customerDescription);
        }
        if (orderDetails.isEmpty() || orderDetails.size() > 1) {
            throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_CAN_NOT_BE_EMPTY, "ORDER_DETAILS_CAN_NOT_BE_EMPTY");
        }
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("CancelOrderController operate executed!");
        // TODO: 4/20/19 bu metot her bir orderda tek ürün varmış gibi çalışıyor!!!

        List<OrderDetail> resultList = orderFacade.cancelOrder(orderId, orderDetails);

        ObjectMapper oMapper = new ObjectMapper();
        CancelOrderOutput output = new CancelOrderOutput(100, resultList);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("CancelOrderController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
