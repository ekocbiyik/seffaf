package com.payment.seffaf.restcontroller.order;

import com.payment.seffaf.model.OrderDetail;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

/**
 * enbiya on 02.04.2019
 */
public class PrepareOrderDetailOutput {

    private int responseCode;
    private OrderDetail orderDetail;

    public PrepareOrderDetailOutput(int responseCode, OrderDetail orderDetail) {
        this.responseCode = responseCode;
        this.orderDetail = orderDetail;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}