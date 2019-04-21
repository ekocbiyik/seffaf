package com.payment.seffaf.restcontroller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
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
public class PrepareOrderController extends SeffafOperationImpl {

    private Map request;
    private UUID orderDetailId;
    private UUID sellerCustomerId;

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
        ValidationUtils.UUIDValidation(request.get("sellerCustomerId").toString());
        orderDetailId = UUID.fromString(request.get("orderDetailId").toString());
        sellerCustomerId = UUID.fromString(request.get("sellerCustomerId").toString());
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("PrepareOrderController operate executed!");

        OrderDetail oDetail = orderFacade.prepareOrder(orderDetailId, sellerCustomerId);

        ObjectMapper oMapper = new ObjectMapper();
        PrepareOrderDetailOutput output = new PrepareOrderDetailOutput(100, oDetail);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("PrepareOrderController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
