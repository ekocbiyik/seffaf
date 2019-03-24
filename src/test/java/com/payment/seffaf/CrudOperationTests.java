package com.payment.seffaf;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.model.Gender;
import com.payment.seffaf.repositories.service.ICustomerService;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * enbiya on 24.03.2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudOperationTests {

    @Autowired
    private ICustomerService customerService;

    @Test
    public void customerTest() {

        Customer c = new Customer();
        c.setName("enbiya");
        c.setSurname("kocbiyik");
        c.setGender(Gender.MALE);
        c.setPhoneNumber("05541111111");
        c.setEmail("enbiya@mail.com");
        c.setPassword("deneme_password");
        c.setActive(true);

        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(c.getPhoneNumber(), "TR");
            System.out.println("phoneNumber isValid : " + phoneUtil.isValidNumber(swissNumberProto));
            System.out.println("e-mail isValid      : " + EmailValidator.getInstance().isValid(c.getEmail()));
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }

        customerService.save(c);
        System.out.println(c.getCustomerId() + ": " + c.getName() + " created!");

        customerService.getAllCustomer().forEach(cc -> System.out.println(c.getCustomerId() + ": " + c.getName()));
//        customerService.getAllCustomer().forEach(cc -> {
//            customerService.delete(cc);
//            System.out.println(c.getCustomerId() + " deleted..");
//        });
    }
}
