package com.payment.seffaf.middleware.facadeimpl;

import com.payment.seffaf.exceptions.SeffafException;
import com.payment.seffaf.exceptions.SeffafExceptionCode;
import com.payment.seffaf.middleware.facade.IOrderFacade;
import com.payment.seffaf.model.*;
import com.payment.seffaf.repositories.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * ekocbiyik on 4/13/19
 */
@Controller
public class OrderFacadeImpl implements IOrderFacade {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Transactional(rollbackFor = SeffafException.class)
    @Override
    public Order createOrder(UUID customerId, UUID addressId, String description, Map<UUID, Integer> pIdList) throws SeffafException {

        Customer deliveredCustomer = customerService.getCustomerById(customerId);
        if (deliveredCustomer == null) {
            throw new SeffafException(SeffafExceptionCode.CUSTOMER_NOT_FOUND, String.format("CUSTOMER_NOT_FOUND: %s", customerId));
        }
        logger.info("customer found: {}", deliveredCustomer.getCustomerId());

        Address deliveredAddress = addressService.getAddressById(addressId);
        if (deliveredAddress == null) {
            throw new SeffafException(SeffafExceptionCode.ADDRESS_NOT_FOUND, String.format("ADDRESS_NOT_FOUND: %s", addressId));
        }
        logger.info("address found: {}", deliveredAddress.getAddressId());

        // ürünler var mı kontrol ediyor
        Map<Product, Integer> productMap = new HashMap<>();
        for (Map.Entry<UUID, Integer> entry : pIdList.entrySet()) {
            UUID pId = entry.getKey();
            int pCount = entry.getValue();

            Product p = productService.getByProductId(pId);
            if (p == null) {
                throw new SeffafException(SeffafExceptionCode.PRODUCT_NOT_FOUND, String.format("PRODUCT_NOT_FOUND: %s", pId));
            }
            if (p.getStockCount() < pCount) {
                throw new SeffafException(SeffafExceptionCode.PRODUCT_STOCK_NOT_ENOUGH, String.format("PRODUCT_STOCK_NOT_ENOUGH: %s, stockCount: %s", pId, p.getStockCount()));
            }
            productMap.put(p, pCount);
            logger.info("product found: {}", p.getProductId());
        }

        /**
         * önce order oluştur, sonra herbir ürün için order detail.
         * kargo ücreti 0 kabul edildi, ürün fiyatına dahil.. sonraki fazlarda burası düzeltilmeli..
         */

        // ürünlerin stok sayısını güncelliyor
        BigDecimal totalAmount = BigDecimal.ZERO;
        Currency currency = Currency.getInstance("TRY");

        for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
            Product product = entry.getKey();
            int pCount = entry.getValue();

            productService.decreaseStockCountByProduct(product, pCount);
            logger.info("productId: {} stock count decreased: {} from: {}", product.getProductId(), pCount, product.getStockCount());

            currency = product.getCurrency();
            totalAmount = totalAmount.add(product.getProductAmount().multiply(BigDecimal.valueOf(pCount)));
            logger.info("totalAmount: {}", totalAmount);
        }

        Order order = new Order();
        order.setDeliveredCustomerId(deliveredCustomer.getCustomerId());
        order.setTotalAmount(totalAmount);
        order.setCurrency(currency);
        orderService.save(order);
        logger.info("order: {} created for customer: {}", order.getOrderId(), deliveredCustomer.getCustomerId());

        for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
            Product product = entry.getKey();
            Integer pCount = entry.getValue();

            OrderDetail detail = new OrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setProductId(product.getProductId());
            detail.setRefunded(product.isRefunded());
            detail.setCount(pCount);
            detail.setDescription(description);
            detail.setProductAmount(product.getProductAmount());
            detail.setTransportAmount(product.getTransportAmount());
            detail.setCurrency(product.getCurrency());
            detail.setSellerCustomerId(product.getCustomerId());
            detail.setSellerAddressId(product.getAddressId());
            detail.setDeliveredCustomerId(deliveredCustomer.getCustomerId());
            detail.setDeliveryAddressId(deliveredAddress.getAddressId());
            detail.setOrderStatus(OrderStatus.IN_PAYMENT); // ödeme alındıktan sonra QUEUE durumuna çekilir.

            orderDetailService.save(detail);
            logger.info("orderDetail: {} created for customer: {}", detail.getOrderDetailId(), deliveredCustomer.getCustomerId());
        }
        return order;
    }
}
