package com.payment.seffaf.restcontroller.product;

import com.payment.seffaf.model.Product;

/**
 * enbiya on 02.04.2019
 */
public class AddProductOutput {

    private int responseCode;
    private Product product;

    public AddProductOutput(int responseCode, Product product) {
        this.responseCode = responseCode;
        this.product = product;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
