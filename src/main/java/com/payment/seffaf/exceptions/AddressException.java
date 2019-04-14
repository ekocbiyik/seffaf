package com.payment.seffaf.exceptions;

/**
 * enbiya on 02.04.2019
 */
public class AddressException extends SeffafException {

    public AddressException(String param) {
        super(SeffafExceptionCode.INVALID_PARAMETER, param);
    }

    public AddressException(int code, String param) {
        super(code, param);
    }

    public AddressException(int code) {
        super(code);
    }

}
