package com.payment.seffaf.restcontroller.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.exceptions.SeffafExceptionOutput;
import com.payment.seffaf.exceptions.ValidationException;
import com.payment.seffaf.middleware.facade.ICustomerFacade;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.model.Gender;
import com.payment.seffaf.operation.SeffafOperationImpl;
import com.payment.seffaf.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * enbiya on 30.03.2019
 */
@Controller
public class AddCustomerController extends SeffafOperationImpl {

    @Autowired
    private ICustomerFacade customerFacade;

    private Map request;
    private Customer customer;

    @Override
    public Object init(Object... params) {
        logger.info("params: {}", params);
        this.request = (Map) params[0];
        return request.toString();
    }

    @Override
    public void validate() throws ValidationException {
        ValidationUtils.textValidation(request.get("name").toString());
        ValidationUtils.textValidation(request.get("surname").toString());
        ValidationUtils.phoneNumberValidation(request.get("phoneNumber").toString());
        ValidationUtils.emailValidation(request.get("email").toString());

        try {
            Gender.valueOf(request.get("gender").toString().toUpperCase());
        } catch (Exception e) {
            throw new ValidationException(SeffafExceptionCode.INVALID_PARAMETER,
                    String.format("invalid parameter: gender(%s)", request.get("gender").toString()));
        }

        customer = new Customer();
        customer.setName(request.get("name").toString());
        customer.setSurname(request.get("surname").toString());
        customer.setGender(Gender.valueOf(request.get("gender").toString().toUpperCase()));
        customer.setPhoneNumber(request.get("phoneNumber").toString());
        customer.setEmail(request.get("email").toString());
    }

    @Override
    public Object operate() {
        logger.info("AddCustomerController operate executed!");
        customerFacade.createCustomer(customer);

        ObjectMapper oMapper = new ObjectMapper();
        AddCustomerOutput output = new AddCustomerOutput(100, customer);
        Map<String, Object> map = oMapper.convertValue(output, Map.class);
        return map;
    }

    @Override
    public Object handleException(Exception e) {
        logger.info("AddCustomerController handleException executed!");
        return new ObjectMapper()
                .convertValue(
                        new SeffafExceptionOutput(99, e.getMessage()),
                        Map.class);
    }
}
