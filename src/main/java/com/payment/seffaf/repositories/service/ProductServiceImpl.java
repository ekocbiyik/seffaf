package com.payment.seffaf.repositories.service;

import com.payment.seffaf.model.Product;
import com.payment.seffaf.repositories.dao.IProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * enbiya on 27.03.2019
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Transactional
    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Transactional
    @Override
    public Product findByProductId(UUID productId) {
        return productDao.findByProductId(productId);
    }

    @Transactional
    @Override
    public List<Product> findAllByCustomerId(UUID customerId) {
        return productDao.findAllByCustomerId(customerId);
    }

    @Transactional
    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productDao.findAll();
    }

}
