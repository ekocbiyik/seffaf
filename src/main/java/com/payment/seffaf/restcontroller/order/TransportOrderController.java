package com.payment.seffaf.restcontroller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.middleware.facade.IOrderFacade;
import com.payment.seffaf.model.OrderDetail;
import com.payment.seffaf.model.OrderStatus;
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
public class TransportOrderController extends SeffafOperationImpl {

    private Map request;
    private UUID orderDetailId;
    private String trackingNumber;
    private OrderStatus orderStatus;

    @Autowired
    private IOrderFacade orderFacade;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws SeffafException {
        ValidationUtils.UUIDValidation(request.get("orderDetailId").toString());
        ValidationUtils.notEmptyStringValidation(request.get("trackingNumber").toString());
        orderStatus = OrderStatus.valueOf(request.get("orderStatus").toString());
        orderDetailId = UUID.fromString(request.get("orderDetailId").toString());
        trackingNumber = request.get("trackingNumber").toString();
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("TransportOrderController operate executed!");

        OrderDetail oDetail = orderFacade.transportOrder(orderDetailId, trackingNumber, orderStatus);

        ObjectMapper oMapper = new ObjectMapper();
        PrepareOrderDetailOutput output = new PrepareOrderDetailOutput(100, oDetail);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("TransportOrderController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
