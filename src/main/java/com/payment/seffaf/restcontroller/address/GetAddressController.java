package com.payment.seffaf.restcontroller.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.exceptions.ValidationException;
import com.payment.seffaf.middleware.facade.IAddressFacade;
import com.payment.seffaf.model.Address;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.repositories.service.ICustomerService;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ekocbiyik on 4/10/19
 */
@Controller
public class GetAddressController extends SeffafOperationImpl {

    @Autowired
    private IAddressFacade addressFacade;

    private Map request;
    private UUID customerId;

    @Override
    public Object init(Object... params) {
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws ValidationException {
        ValidationUtils.UUIDValidation(request.get("customerId").toString());
        customerId = UUID.fromString(request.get("customerId").toString());
    }

    @Override
    public Object operate() throws SeffafException {
        logger.info("GetAddressController operate executed!");
        List<Address> customerAddressList = addressFacade.getAddressByCustomer(customerId);

        ObjectMapper oMapper = new ObjectMapper();
        GetAddressOutput output = new GetAddressOutput(100, customerAddressList);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("GetAddressController handleException executed!");
        int errorCode = (e instanceof SeffafException) ? ((SeffafException) e).getCode() : 99;
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(errorCode, e.getMessage() == null ? e.toString() : e.getMessage()),
                        Map.class);
    }
}
