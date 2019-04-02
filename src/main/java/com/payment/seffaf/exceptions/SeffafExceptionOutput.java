package com.payment.seffaf.exceptions;

/**
 * enbiya on 02.04.2019
 */
public class SeffafExceptionOutput {

    private int responseCode;
    private String message;

    public SeffafExceptionOutput(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
