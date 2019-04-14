package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.Payment;

/**
 * ekocbiyik on 4/10/19
 */
public interface IPaymentFacade {

    Payment createPayment(Payment payment) throws SeffafException;

}
