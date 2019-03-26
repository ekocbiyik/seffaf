package com.payment.seffaf;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.payment.seffaf.model.*;
import com.payment.seffaf.repositories.service.IAddressService;
import com.payment.seffaf.repositories.service.IBankAccountService;
import com.payment.seffaf.repositories.service.ICustomerService;
import com.payment.seffaf.repositories.service.IPaymentService;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

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

    @Autowired
    private IPaymentService paymentService;


    @Test
    public void genericTest() {
        customerTest();
        addressTest();
        bankAccountTest();
        paymentTest();
    }

    @Test
    public void customerTest() {

        List<Customer> customerList = new ArrayList<>();
        Customer c = null;
        for (int i = 0; i < 5; i++) {
            c = new Customer();
            c.setName("enbiya" + i);
            c.setSurname("kocbiyik");
            c.setGender(Gender.MALE);
            c.setPhoneNumber("0554111111" + i);
            c.setEmail("enbiya" + i + "@mail.com");
            c.setPassword("deneme_password");
            c.setActive(true);
            customerList.add(c);
        }


        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(c.getPhoneNumber(), "TR");
            System.out.println("phoneNumber isValid : " + phoneUtil.isValidNumber(swissNumberProto));
            System.out.println("e-mail isValid      : " + EmailValidator.getInstance().isValid(c.getEmail()));
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }

        customerList.forEach(cc -> customerService.save(cc));

        customerService.getAllCustomer().forEach(cc -> System.out.println(cc.getCustomerId() + ": " + cc.getName()));
//        customerService.getAllCustomer().forEach(cc -> {
//            customerService.delete(cc);
//            System.out.println(c.getCustomerId() + " deleted..");
//        });
    }

    @Test
    public void addressTest() {

        List<Customer> allCustomer = customerService.getAllCustomer();
        if (allCustomer.size() < 0) return;

        allCustomer.forEach(c -> {
            Address a = new Address();
            a.setCustomer(c);
            a.setName(c.getName());
            a.setSurname(c.getSurname());
            a.setPhoneNumber(c.getPhoneNumber());
            a.setAddressTitle(c.getName() + "- Ev Adresim");
            a.setCountry(new Locale("", "TR").getDisplayName());
            a.setCity("Trabzon");
            a.setDistrict("OF");
            a.setDetail(c.getEmail() + " - yola çıkmadan arayınız.");

            addressService.save(a);
        });

        addressService.getAllAddresses().forEach(aa -> System.out.println(aa.getAddressId() + " " + aa.getAddressTitle()));
    }

    @Test
    public void bankAccountTest() {

        List<Customer> allCustomer = customerService.getAllCustomer();
        if (allCustomer.size() < 0) return;

        allCustomer.forEach(c -> {

            String d1 = String.format("%04d", new Random().nextInt(10000));
            String d2 = String.format("%04d", new Random().nextInt(10000));
            String d3 = String.format("%04d", new Random().nextInt(10000));
            String d4 = String.format("%04d", new Random().nextInt(10000));

            BankAccount account = new BankAccount();
            account.setCardHolderName(c.getName().toUpperCase() + " " + c.getSurname().toUpperCase());
            account.setCardNumber(d1 + "-" + d2 + "-" + d3 + "-" + d4);
            account.setIbanNumber("TR610000000000000000" + d1);
            account.setCustomer(c);
            accountService.save(account);
        });

        accountService.getAllBankAccounts().forEach(a -> System.out.println(a.getCardNumber()));
    }

    @Test
    public void paymentTest() {

        BigDecimal payment = new BigDecimal("1115.37");
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        double doublePayment = payment.doubleValue();
        String s = n.format(doublePayment);
        System.out.println(s);

        List<Customer> allCustomer = customerService.getAllCustomer();
        if (allCustomer.size() < 2) return;

        List<BankAccount> allBankAccounts = accountService.getAllBankAccounts();

        Payment p = new Payment();
//        p.setOrder(new Order());
        p.setBankAccount(allBankAccounts.get(0));
        p.setSellerCustomer(allCustomer.get(0));
        p.setDeliveredCustomer(allCustomer.get(1));
        p.setCurrency(Currency.getInstance("TRY"));
        p.setPaymentAmount(new BigDecimal("61.50"));
        p.setVposCode("12");
        p.setVposRefCode("32323");
        p.setBankCode("453452");

        paymentService.save(p);

        paymentService.getAllPayments().forEach(payment1 -> System.out.println(p.getPaymentId()));

    }

}
