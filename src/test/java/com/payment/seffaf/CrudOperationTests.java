package com.payment.seffaf;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.payment.seffaf.model.Address;
import com.payment.seffaf.model.BankAccount;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.model.Gender;
import com.payment.seffaf.repositories.service.IAddressService;
import com.payment.seffaf.repositories.service.IBankAccountService;
import com.payment.seffaf.repositories.service.ICustomerService;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

/**
 * enbiya on 24.03.2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudOperationTests {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IBankAccountService accountService;

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

    @Test
    public void addressTest() {

        List<Customer> allCustomer = customerService.getAllCustomer();
        if (allCustomer.size() < 0) return;
        Customer customer = allCustomer.get(0);

        List<Address> addressList = addressService.getAllByCustomer(customer);
        System.out.println(addressList);

        Address a = new Address();
        a.setCustomer(customer);
        a.setName("enbiya");
        a.setSurname("kocbiyik");
        a.setPhoneNumber(customer.getPhoneNumber());
        a.setAddressTitle("Ev Adresim");
        a.setCountry(new Locale("", "TR").getDisplayName());
        a.setCity("Trabzon");
        a.setDistrict("OF");
        a.setDetail("yola çıkmadan arayınız.");

        addressService.save(a);

        addressService.getAllByCustomer(customer).forEach(aa -> System.out.println(aa.getAddressId() + " " + aa.getAddressTitle()));

    }

    @Test
    public void bankAccountTest() {

        List<Customer> allCustomer = customerService.getAllCustomer();
        if (allCustomer.size() < 0) return;
        Customer customer = allCustomer.get(0);

        BankAccount account = new BankAccount();
        account.setCardHolderName("Enbiya KOÇBIYIK");
        account.setCardNumber("1111-2222-3333-4444");
        account.setIbanNumber("TR6100000000000000000001");
        account.setCustomer(customer);

        accountService.save(account);

    }

}
