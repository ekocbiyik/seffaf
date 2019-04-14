package com.payment.seffaf.middleware.facade;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.Product;

import java.util.UUID;

/**
 * ekocbiyik on 4/13/19
 */
public interface IProductFacade {

    Product createProduct(Product product) throws SeffafException;

    Product getProduct(UUID productId) throws SeffafException;

}
