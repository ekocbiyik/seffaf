package com.payment.seffaf.operation;

/**
 * enbiya on 30.03.2019
 */
public interface ISeffafOperationExecutor<T> {

    T execute(String source, String operation, Object... params);

}
