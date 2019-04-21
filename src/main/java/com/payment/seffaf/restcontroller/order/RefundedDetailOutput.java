package com.payment.seffaf.restcontroller.order;

import com.payment.seffaf.model.RefundedDetail;

/**
 * enbiya on 02.04.2019
 */
public class RefundedDetailOutput {

    private int responseCode;
    private RefundedDetail refundedDetail;

    public RefundedDetailOutput(int responseCode, RefundedDetail refundedDetail) {
        this.responseCode = responseCode;
        this.refundedDetail = refundedDetail;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public RefundedDetail getRefundedDetail() {
        return refundedDetail;
    }

    public void setRefundedDetail(RefundedDetail refundedDetail) {
        this.refundedDetail = refundedDetail;
    }
}