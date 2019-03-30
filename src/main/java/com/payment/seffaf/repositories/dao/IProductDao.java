package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
@Repository
public interface IProductDao extends CrudRepository<Product, UUID> {

    Product findByProductId(UUID uuid);

    List<Product> findAllByCustomerId(UUID customerId);

}
