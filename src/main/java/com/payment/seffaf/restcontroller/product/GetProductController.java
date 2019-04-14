package com.payment.seffaf.restcontroller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.exceptions.ValidationException;
import com.payment.seffaf.middleware.facade.IProductFacade;
import com.payment.seffaf.model.Product;
import com.payment.seffaf.operation.SeffafOperationImpl;
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
public class GetProductController extends SeffafOperationImpl {

    @Autowired
    private IProductFacade productFacade;

    private Map request;
    private UUID productId;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws ValidationException {
        ValidationUtils.UUIDValidation(request.get("productId").toString());
        productId = UUID.fromString(request.get("productId").toString());
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("GetProductController operate executed!");

        Product product = productFacade.getProduct(productId);
        ObjectMapper oMapper = new ObjectMapper();
        AddProductOutput output = new AddProductOutput(100, product);
        Map<String, Object> map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("GetProductController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
