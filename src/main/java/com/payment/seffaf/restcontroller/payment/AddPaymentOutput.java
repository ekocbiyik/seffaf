package com.payment.seffaf.restcontroller.payment;

import com.payment.seffaf.model.Payment;

/**
 * enbiya on 02.04.2019
 */
public class AddPaymentOutput {

    private int responseCode;
    private Payment payment;

    public AddPaymentOutput(int responseCode, Payment payment) {
        this.responseCode = responseCode;
        this.payment = payment;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
