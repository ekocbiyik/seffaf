package com.payment.seffaf.exceptions;

/**
 * enbiya on 02.04.2019
 */
public class ProductException extends SeffafException {

    public ProductException(String param) {
        super(SeffafExceptionCode.INVALID_PARAMETER, param);
    }

    public ProductException(int code, String param) {
        super(code, param);
    }

    public ProductException(int code) {
        super(code);
    }

}
