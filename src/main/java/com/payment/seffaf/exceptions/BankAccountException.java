package com.payment.seffaf.exceptions;

/**
 * enbiya on 02.04.2019
 */
public class BankAccountException extends SeffafException {

    public BankAccountException(String param) {
        super(SeffafExceptionCode.INVALID_PARAMETER, param);
    }

    public BankAccountException(int code, String param) {
        super(code, param);
    }

    public BankAccountException(int code) {
        super(code);
    }

}
