package com.payment.seffaf.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * enbiya on 30.03.2019
 */
public abstract class SeffafOperationImpl<T> implements ISeffafOperation<T> {

    final protected Logger logger = LoggerFactory.getLogger(getClass().getName());

}
