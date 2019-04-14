package com.payment.seffaf.restcontroller.address;

import com.payment.seffaf.model.Address;

import java.util.List;

/**
 * ekocbiyik on 4/10/19
 */
public class GetAddressOutput {

    private int responseCode;
    private List<Address> address;

    public GetAddressOutput(int responseCode, List<Address> addressList) {
        this.responseCode = responseCode;
        this.address = addressList;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
