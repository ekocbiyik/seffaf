package com.payment.seffaf.operation;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.ValidationException;

/**
 * enbiya on 30.03.2019
 */
public interface ISeffafOperation<T> {

    T init(Object... params);

    void validate() throws SeffafException;

    T operate() throws SeffafException;

    T handleException(Exception e);

}
