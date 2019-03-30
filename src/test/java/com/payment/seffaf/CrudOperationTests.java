package com.payment.seffaf;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.payment.seffaf.model.*;
import com.payment.seffaf.repositories.service.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
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

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IPaymentService paymentService;


    @Test
    public void genericTest() throws NumberParseException {

        /**
         * linke tıklandı, ürün ekranı açıldı
         * adres bilgileri girildi, buton tıklandı
         * ürüne ait fiyat ekranı geldi
         * ödeme yap butonu tıklandı,
         *      customer oluşturuldu
         *      address oluşturuldu
         *      order oluşturuldu
         *      orderDetail oluşturuldu (IN_PAYMENT)
         * insert başarılıysa ödeme ekranına yönlendirildi
         * ödeme yapıldı
         *      payment oluşturuldu
         *      status IN_QUEUE çekildi
         * */


        // ornek bir senaryo
        customerTest();
        addressTest();
        bankAccountTest();
        productTest();
        orderTest();
        paymentTest();
    }

    @Test
    public void customerTest() throws NumberParseException {


        Customer customer = new Customer();
        customer.setName("Enbiya");
        customer.setSurname("KOCBIYIK");
        customer.setGender(Gender.MALE);
        customer.setPhoneNumber("0554111111");
        customer.setEmail("enbiya@mail.com");
        customer.setPassword(null);
        customer.setActive(true);

        customerService.save(customer);
        customerService.getAllCustomer().forEach(c -> System.out.println(c.getEmail()));

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(customer.getPhoneNumber(), "TR");
        System.out.println("phoneNumber isValid : " + phoneUtil.isValidNumber(swissNumberProto));
        System.out.println("e-mail isValid      : " + EmailValidator.getInstance().isValid(customer.getEmail()));
    }

    @Test
    public void addressTest() {

        Customer customer = customerService.getAllCustomer().get(0);

        Address address = new Address();
        address.setCustomerId(customer.getCustomerId());
        address.setName(customer.getName());
        address.setSurname(customer.getSurname());
        address.setPhoneNumber(customer.getPhoneNumber());
        address.setAddressTitle("Iş Adresim");
        address.setCountry(new Locale("", "TR").getDisplayName());
        address.setCity("Istanbul");
        address.setDistrict("Umraniye");
        address.setDetail("Sekretere teslim ediniz.");

        addressService.save(address);

        addressService.getAllAddresses().forEach(aa -> System.out.println(aa.getAddressId() + " " + aa.getAddressTitle()));
    }

    @Test
    public void bankAccountTest() {

        Customer customer = customerService.getAllCustomer().get(0);

        BankAccount b = new BankAccount();
        b.setCustomerId(customer.getCustomerId());
        b.setCardNumber("4322123412341234");
        b.setCardHolderName("Enbiya KOçbıyık".toUpperCase());
        b.setIbanNumber("TR000000000000000001"); // kaçbasamklı kontrol edilmeli..

        accountService.save(b);

        accountService.getAllBankAccounts().forEach(a -> System.out.println(a.getCardNumber()));
    }

    @Test
    public void productTest() {

        Customer customer = customerService.getAllCustomer().get(0);

        Product p = new Product();
        p.setCustomerId(customer.getCustomerId());
        p.setImageUrl("ekocbiyik.com/image");
        p.setProductAmount(new BigDecimal("61.50"));
        p.setTransportAmount(BigDecimal.ZERO);
        p.setCurrency(Currency.getInstance("TRY"));
        p.setStockCount(1);
        p.setCompanyName("Seffaf A.S");
        p.setTitle("Kupa Bardak");
        p.setDescription("ismi özel bardak..");
        p.setEstimateDay(2);

        productService.save(p);

        productService.getAllProducts().forEach(pp -> System.out.println(p.getTitle()));
    }

    @Test
    public void orderTest() {

        Customer customer = customerService.getAllCustomer().get(0);
        Product product = productService.getAllProducts().get(0);
        Address address = addressService.getAllByCustomerId(customer.getCustomerId()).get(0);

        Order order = new Order();
        order.setDeliveredCustomerId(customer.getCustomerId());
        order.setTotalAmount(product.getProductAmount().add(product.getTransportAmount()));
        order.setCurrency(product.getCurrency());
        orderService.save(order);


        OrderDetail d = new OrderDetail();
        d.setOrderId(order.getOrderId());
        d.setProductId(product.getProductId());
        d.setCount(1);
        d.setDescription("ajdgfadsf");
        d.setProductAmount(product.getProductAmount());
        d.setTransportAmount(BigDecimal.ZERO);
        d.setCurrency(product.getCurrency());
        d.setTrackingNumber(null);
        d.setSellerCustomerId(customer.getCustomerId());
        d.setDeliveredCustomerId(customer.getCustomerId());
        d.setSellerAddressId(address.getAddressId());
        d.setDeliveryAddressId(address.getAddressId());
        d.setOrderStatus(OrderStatus.IN_PAYMENT);
        orderDetailService.save(d);

        orderDetailService.getAllOrderDetails().forEach(oDetail -> System.out.println(oDetail.getOrderDetailId()));
    }

    @Test
    public void paymentTest() {

        BigDecimal payment = new BigDecimal("1115.37");
        NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("TR"));
        double doublePayment = payment.doubleValue();
        String s = n.format(doublePayment);
        System.out.println(s);


        Customer customer = customerService.getAllCustomer().get(0);
        BankAccount account = accountService.getAllBankAccounts().get(0);
        Order order = orderService.getAllOrders().get(0);


        Payment p = new Payment();
        p.setOrderId(order.getOrderId());
        p.setAccountId(account.getAccountId());
        p.setDeliveredCustomerId(customer.getCustomerId());
        p.setCurrency(order.getCurrency());
        p.setPaymentAmount(order.getTotalAmount());
        p.setVposCode("12");
        p.setVposRefCode("32323");
        p.setBankCode("453452"); // ödemeyi alan banka kodu

        paymentService.save(p);

        paymentService.getAllPayments().forEach(payment1 -> System.out.println(p.getPaymentId()));

    }

}
