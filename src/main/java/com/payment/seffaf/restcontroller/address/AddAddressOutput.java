package com.payment.seffaf.restcontroller.address;

import com.payment.seffaf.model.Address;

/**
 * ekocbiyik on 4/10/19
 */
public class AddAddressOutput {

    private int responseCode;
    private Address address;

    public AddAddressOutput(int responseCode, Address address) {
        this.responseCode = responseCode;
        this.address = address;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
