package com.payment.seffaf.restcontroller.customer;

import com.payment.seffaf.model.Customer;

/**
 * enbiya on 02.04.2019
 */
public class AddCustomerOutput {

    private int responseCode;
    private Customer customer;

    public AddCustomerOutput(int responseCode, Customer customer) {
        this.responseCode = responseCode;
        this.customer = customer;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
