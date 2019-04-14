package com.payment.seffaf.restcontroller.order;

import com.payment.seffaf.model.Order;

/**
 * enbiya on 02.04.2019
 */
public class AddOrderOutput {

    private int responseCode;
    private Order order;

    public AddOrderOutput(int responseCode, Order order) {
        this.responseCode = responseCode;
        this.order = order;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}