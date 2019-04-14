package com.payment.seffaf.restcontroller.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.middleware.facade.IPaymentFacade;
import com.payment.seffaf.model.Payment;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.restcontroller.product.AddProductOutput;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.UUID;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class AddPaymentController extends SeffafOperationImpl {

    private Map request;
    private Payment payment;


    @Autowired
    private IPaymentFacade paymentFacade;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws SeffafException {
        ValidationUtils.UUIDValidation(request.get("orderId").toString());
        ValidationUtils.UUIDValidation(request.get("customerId").toString());
        ValidationUtils.currencyValidation(request.get("currency").toString());
        ValidationUtils.amountValidation(request.get("paymentAmount").toString());
        ValidationUtils.textValidation(request.get("bankCode").toString());

        UUID orderId = UUID.fromString(request.get("orderId").toString());
        UUID customerId = UUID.fromString(request.get("customerId").toString());
        Currency currency = Currency.getInstance(request.get("currency").toString());
        BigDecimal paymentAmount = new BigDecimal(request.get("paymentAmount").toString());
        String bankCode = request.get("bankCode").toString();

        payment = new Payment();
        payment.setOrderId(orderId);
        payment.setDeliveredCustomerId(customerId);
        payment.setCurrency(currency);
        payment.setPaymentAmount(paymentAmount);
        payment.setBankCode(bankCode);
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("AddPaymentController operate executed!");
        paymentFacade.createPayment(payment);

        ObjectMapper oMapper = new ObjectMapper();
        AddPaymentOutput output = new AddPaymentOutput(100, payment);
        Map<String, Object> map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddPaymentController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
