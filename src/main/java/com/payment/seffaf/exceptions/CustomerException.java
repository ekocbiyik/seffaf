package com.payment.seffaf.exceptions;

/**
 * enbiya on 02.04.2019
 */
public class CustomerException extends SeffafException {

    public CustomerException(String param) {
        super(SeffafExceptionCode.INVALID_PARAMETER, param);
    }

    public CustomerException(int code, String param) {
        super(code, param);
    }

    public CustomerException(int code) {
        super(code);
    }

}
