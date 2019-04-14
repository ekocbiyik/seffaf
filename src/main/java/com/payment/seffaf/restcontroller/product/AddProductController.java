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
public class AddProductController extends SeffafOperationImpl {

    @Autowired
    private IProductFacade productFacade;

    private Map request;
    private Product product;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws ValidationException {

        ValidationUtils.UUIDValidation(request.get("customerId").toString());
        ValidationUtils.UUIDValidation(request.get("addressId").toString());
        ValidationUtils.UUIDValidation(request.get("accountId").toString());
        ValidationUtils.booleanValidation(request.get("isRefunded").toString());    // iade edilebilir mi?
        ValidationUtils.amountValidation(request.get("productAmount").toString());
        ValidationUtils.numberValidation(request.get("stockCount").toString());     // -1 ise sınırsız stok
        ValidationUtils.textValidation(request.get("companyName").toString());
        ValidationUtils.numberValidation(request.get("estimateDay").toString());

        product = new Product();
        product.setCustomerId(UUID.fromString(request.get("customerId").toString()));
        product.setAddressId(UUID.fromString(request.get("addressId").toString()));
        product.setAccountId(UUID.fromString(request.get("accountId").toString()));
        product.setRefunded(Boolean.valueOf(request.get("isRefunded").toString()));
        product.setImageUrl(request.get("imageUrl").toString());
        product.setProductAmount(new BigDecimal(request.get("productAmount").toString()));
        product.setTransportAmount(BigDecimal.ZERO); // şimdilik 0
        product.setCurrency(Currency.getInstance("TRY"));
        product.setStockCount(Integer.parseInt(request.get("stockCount").toString()));
        product.setCompanyName(request.get("companyName").toString());
        product.setTitle(request.get("title").toString());
        product.setDescription(request.get("description").toString());
        product.setEstimateDay(Integer.parseInt(request.get("estimateDay").toString()));
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("AddProductController operate executed!");
        productFacade.createProduct(product);

        ObjectMapper oMapper = new ObjectMapper();
        AddProductOutput output = new AddProductOutput(100, product);
        Map<String, Object> map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddProductController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
