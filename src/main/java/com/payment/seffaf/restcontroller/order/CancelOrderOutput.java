package com.payment.seffaf.restcontroller.order;

import com.payment.seffaf.model.OrderDetail;

import java.util.List;

/**
 * enbiya on 02.04.2019
 */
public class CancelOrderOutput {

    private int responseCode;
    private List<OrderDetail> orderDetailList;

    public CancelOrderOutput(int responseCode, List<OrderDetail> orderDetailList) {
        this.responseCode = responseCode;
        this.orderDetailList = orderDetailList;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

}