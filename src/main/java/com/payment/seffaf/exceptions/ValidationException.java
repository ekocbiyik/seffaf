package com.payment.seffaf.exceptions;

/**
 * enbiya on 02.04.2019
 */
public class ValidationException extends SeffafException {

    public ValidationException(String param) {
        super(SeffafExceptionCode.INVALID_PARAMETER, param);
    }

    public ValidationException(int code, String param) {
        super(code, param);
    }

    public ValidationException(int code) {
        super(code);
    }

}
