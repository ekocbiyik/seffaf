package com.payment.seffaf.restcontroller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.middleware.facade.IOrderFacade;
import com.payment.seffaf.model.OrderDetail;
import com.payment.seffaf.model.RefundedDetail;
import com.payment.seffaf.model.RefundedStatus;
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
public class DeliverRefundedController extends SeffafOperationImpl {

    private Map request;
    private UUID sellerCustomerId;
    private UUID refundedDetailId;
    private RefundedStatus refundedStatus;
    private String sellerDescription;

    @Autowired
    private IOrderFacade orderFacade;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws SeffafException {
        ValidationUtils.UUIDValidation(request.get("sellerCustomerId").toString());
        ValidationUtils.UUIDValidation(request.get("refundedDetailId").toString());
        sellerCustomerId = UUID.fromString(request.get("sellerCustomerId").toString());
        refundedDetailId = UUID.fromString(request.get("refundedDetailId").toString());
        refundedStatus = RefundedStatus.valueOf(request.get("refundedStatus").toString());

        if (refundedStatus == RefundedStatus.REFUSED) {
            ValidationUtils.notEmptyStringValidation(request.get("sellerDescription").toString());
            sellerDescription = request.get("sellerDescription").toString();
        }
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("DeliverRefundedController operate executed!");

        RefundedDetail rDetail = orderFacade.deliverRefunded(sellerCustomerId, refundedDetailId, refundedStatus, sellerDescription);

        ObjectMapper oMapper = new ObjectMapper();
        RefundedDetailOutput output = new RefundedDetailOutput(100, rDetail);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("DeliverRefundedController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
