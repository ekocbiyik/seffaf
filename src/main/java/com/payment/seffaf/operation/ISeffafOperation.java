package com.payment.seffaf.operation;

/**
 * enbiya on 30.03.2019
 */
public interface ISeffafOperation<T> {

    void init(Object... params);

    void validate();

    T operate();

    T handleException(Exception e);

}
