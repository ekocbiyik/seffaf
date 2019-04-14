package com.payment.seffaf.repositories.service;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.model.Product;
import com.payment.seffaf.repositories.dao.IProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IProductDao productDao;

    @Transactional
    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Transactional
    @Override
    public Product getByProductId(UUID productId) {
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

    @Transactional
    @Override
    public synchronized boolean decreaseStockCountByProduct(Product product, int stockCount) throws SeffafException {
        Product p = productDao.findByProductIdAndStockCount(product.getProductId(), stockCount);
        if (p == null) {
            throw new SeffafException(SeffafExceptionCode.PRODUCT_STOCK_NOT_ENOUGH, String.format("PRODUCT_STOCK_NOT_ENOUGH: %s, stockCount: %s", product.getProductId(), p.getStockCount()));
        }
        logger.info("product found with {} stock size!", p.getStockCount());

        p.setStockCount(p.getStockCount() - stockCount);
        save(p);
        logger.info("product stocksize updated: {}!", p.getStockCount());
        return true;
    }

    @Transactional
    @Override
    public synchronized boolean increaseStockCountByProduct(Product product, int stockCount) throws SeffafException {
        Product p = productDao.findByProductId(product.getProductId());
        if (p == null) {
            throw new SeffafException(SeffafExceptionCode.PRODUCT_NOT_FOUND, String.format("PRODUCT_NOT_FOUND: %s", product.getProductId()));
        }
        logger.info("product found with {} stock size!", p.getStockCount());

        p.setStockCount(p.getStockCount() + stockCount);
        save(p);
        logger.info("product stocksize updated: {}!", p.getStockCount());
        return true;
    }


}
