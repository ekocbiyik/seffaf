package com.payment.seffaf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.payment.seffaf.model.Customer;
import com.payment.seffaf.restcontroller.customer.AddCustomerOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeffafApplicationTests {

    @Test
    public void contextLoads() throws IOException {

        String request = "{\n" +
                "\t\"name\":\"Enbiya\",\n" +
                "\t\"surname\":\"KOÇBIYIK\",\n" +
                "\t\"gender\":0,\n" +
                "\t\"phoneNumber\":\"+905542771013\",\n" +
                "\t\"email\":\"enbiya@mail.com\"\n" +
                "}";


        ObjectMapper mapper = new ObjectMapper();
        AddCustomerOutput addCustomerOutput = mapper.readValue(request, AddCustomerOutput.class);
        Customer customer = mapper.readValue(request, Customer.class);
        MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        Map<String, Object> params = mapper.readValue(request, type);

        System.out.println(addCustomerOutput);
        System.out.println(customer);
        System.out.println(params);


    }

}
