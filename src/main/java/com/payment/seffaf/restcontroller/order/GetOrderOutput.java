package com.payment.seffaf.restcontroller.order;

import com.payment.seffaf.model.Order;
import com.payment.seffaf.model.OrderDetail;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

/**
 * enbiya on 02.04.2019
 */
public class GetOrderOutput {

    private int responseCode;
    private UUID orderId;
    private UUID deliveredCustomerId;
    private BigDecimal totalAmount;
    private Currency currency;
    private List<OrderDetail> orderDetails;

    public GetOrderOutput(int responseCode, Order order, List<OrderDetail> orderDetails) {
        this.responseCode = responseCode;
        this.orderId = order.getOrderId();
        this.deliveredCustomerId = order.getDeliveredCustomerId();
        this.totalAmount = order.getTotalAmount();
        this.currency = order.getCurrency();
        this.orderDetails = orderDetails;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getDeliveredCustomerId() {
        return deliveredCustomerId;
    }

    public void setDeliveredCustomerId(UUID deliveredCustomerId) {
        this.deliveredCustomerId = deliveredCustomerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}