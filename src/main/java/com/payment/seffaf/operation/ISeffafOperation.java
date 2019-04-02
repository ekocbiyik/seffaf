package com.payment.seffaf.operation;

import com.payment.seffaf.exceptions.ValidationException;

/**
 * enbiya on 30.03.2019
 */
public interface ISeffafOperation<T> {

    T init(Object... params);

    void validate() throws ValidationException;

    T operate();

    T handleException(Exception e);

}
