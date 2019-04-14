package com.payment.seffaf.restcontroller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.middleware.facade.IOrderFacade;
import com.payment.seffaf.model.Order;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.UUID;

/**
 * ekocbiyik on 4/14/19
 */
@Controller
public class CancelOrderController extends SeffafOperationImpl {

    private Map request;
    private UUID orderId;

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
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("CancelOrderController operate executed!");
        Order order = orderFacade.cancelOrder(orderId);

        ObjectMapper oMapper = new ObjectMapper();
        AddOrderOutput output = new AddOrderOutput(100, order);
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
