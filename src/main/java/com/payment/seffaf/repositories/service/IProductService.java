package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
public interface IProductService {

    void save(Product product);

    Product findByProductId(UUID uuid);

    List<Product> findAllByCustomerId(UUID customerId);

    List<Product> getAllProducts();

}
