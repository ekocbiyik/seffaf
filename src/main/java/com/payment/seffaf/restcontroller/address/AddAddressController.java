package com.payment.seffaf.restcontroller.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.exceptions.ValidationException;
import com.payment.seffaf.middleware.facade.IAddressFacade;
import com.payment.seffaf.model.Address;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.UUID;

/**
 * ekocbiyik on 4/10/19
 */
@Controller
public class AddAddressController extends SeffafOperationImpl {

    @Autowired
    private IAddressFacade addressFacade;

    private Map request;
    private Address address;

    @Override
    public Object init(Object... params) {
        logger.info("params: {}", params);
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws ValidationException {
        ValidationUtils.UUIDValidation(request.get("customerId").toString());
        ValidationUtils.textValidation(request.get("name").toString());
        ValidationUtils.textValidation(request.get("surname").toString());
        ValidationUtils.phoneNumberValidation(request.get("phoneNumber").toString());
        ValidationUtils.textValidation(request.get("country").toString());
        ValidationUtils.textValidation(request.get("city").toString());
        ValidationUtils.textValidation(request.get("district").toString());

        address = new Address();
        address.setCustomerId(UUID.fromString(request.get("customerId").toString()));
        address.setName(request.get("name").toString());
        address.setSurname(request.get("surname").toString());
        address.setPhoneNumber(request.get("phoneNumber").toString());
        address.setCountry(request.get("country").toString());
        address.setCity(request.get("city").toString());
        address.setDistrict(request.get("district").toString());

        //optinal
        if (request.containsKey("addressTitle")) {
            address.setAddressTitle(request.get("addressTitle").toString());
        }
        if (request.containsKey("detail")) {
            address.setDetail(request.get("detail").toString());
        }
    }

    @Override
    public Object operate() {
        logger.info("AddAddressController operate executed!");
        addressFacade.createAddress(address);

        ObjectMapper oMapper = new ObjectMapper();
        AddAddressOutput output = new AddAddressOutput(100, address);
        Map map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddAddressController handleException executed!");
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(99, e.getMessage()),
                        Map.class);
    }
}
