package com.payment.seffaf.repositories.dao;

import com.payment.seffaf.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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


    @Query(value = "from Product p where p.productId =:pId and p.stockCount >= :sCount")
    Product findByProductIdAndStockCount(@Param("pId") UUID pId, @Param("sCount") int stockCount);

}
