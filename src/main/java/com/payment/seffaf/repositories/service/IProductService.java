package com.payment.seffaf.repositories.service;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
public interface IProductService {

    void save(Product product);

    Product getByProductId(UUID uuid);

    List<Product> findAllByCustomerId(UUID customerId);

    List<Product> getAllProducts();

    boolean decreaseStockCountByProduct(Product product, int stockCount) throws SeffafException;

    boolean increaseStockCountByProduct(Product product, int stockCount) throws SeffafException;

}
