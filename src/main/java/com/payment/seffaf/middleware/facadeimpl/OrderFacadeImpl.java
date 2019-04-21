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

    @Autowired
    private IPaymentDetailService paymentDetailService;

    @Autowired
    private IRefundedService refundedService;

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

    @Override
    public Map getOrder(UUID orderId) throws SeffafException {
        /** bu metot ordera ait tüm order detailleri döner */

        Order order = orderService.getOrderByOrderId(orderId);
        if (order == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_FOUND, String.format("ORDER_NOT_FOUND: %s", orderId));
        }
        logger.info("order found: {}", order.getOrderId());

        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        if (orderDetails.size() == 0) {
            throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_NOT_FOUND, String.format("ORDER_DETAILS_NOT_FOUND: %s", orderId));
        }
        logger.info("orderDetail size: {} ", orderDetails.size());

        Map result = new HashMap();
        result.put("order", order);
        result.put("orderDetails", orderDetails);
        return result;
    }

    @Override
    public OrderDetail prepareOrder(UUID orderDetailId, UUID sellerCustomerId) throws SeffafException {
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        if (orderDetail == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_NOT_FOUND, String.format("ORDER_DETAILS_NOT_FOUND: %s", orderDetailId));
        }

        if (!orderDetail.getSellerCustomerId().equals(sellerCustomerId)) {
            throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_NOT_FOUND, String.format("ORDER_DETAILS_NOT_FOUND for seller: %s", sellerCustomerId));
        }

        if (orderDetail.getOrderStatus() != OrderStatus.IN_QUEUE) {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_IN_QUEUE_STAGE, String.format("ORDER_NOT_IN_QUEUE_STAGE: %s", orderDetailId));
        }

        logger.info("orderDetailStatus: {}", orderDetail.getOrderStatus());
        orderDetail.setOrderStatus(OrderStatus.IN_PREPARE);
        orderDetailService.save(orderDetail);
        logger.info("orderDetail: {} status updated:", orderDetailId, orderDetail.getOrderStatus());
        return orderDetail;
    }

    @Override
    public OrderDetail transportOrder(UUID orderDetailId, String trackingNumber, OrderStatus orderStatus) throws SeffafException {

        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        if (orderDetail == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_NOT_FOUND, String.format("ORDER_DETAILS_NOT_FOUND: %s", orderDetailId));
        }

        // gelen durum hazırlanıyorsa ve gelen status kargo ise kargoya verildi demek.
        if (orderStatus == OrderStatus.IN_TRANSPORT && orderDetail.getOrderStatus() == OrderStatus.IN_PREPARE) {
            logger.info("orderDetailStatus: {}", orderDetail.getOrderStatus());
            orderDetail.setOrderStatus(OrderStatus.IN_TRANSPORT);
            orderDetail.setTrackingNumber(trackingNumber);
            orderDetail.setShippedDate(new Date());
            orderDetailService.save(orderDetail);
            logger.info("orderDetail: {} status updated: {}, trackingNumber: {}", orderDetailId, orderDetail.getOrderStatus(), trackingNumber);
        } else if (orderStatus == OrderStatus.IN_APPROVAL && orderDetail.getOrderStatus() == OrderStatus.IN_TRANSPORT) {
            // gelen durum teslim edildi ise ve sipariş durumu kardoda ise müşteri kargoyu aldı demek
            logger.info("orderDetailStatus: {}", orderDetail.getOrderStatus());
            orderDetail.setOrderStatus(OrderStatus.IN_APPROVAL);
            orderDetail.setReceivedDate(new Date());
            orderDetailService.save(orderDetail);
            logger.info("orderDetail: {} status updated: {}, trackingNumber: {}", orderDetailId, orderDetail.getOrderStatus(), trackingNumber);

        } else {
            throw new SeffafException(SeffafExceptionCode.ORDER_UNKNOWN_STAGE, String.format("ORDER_UNKNOWN_STAGE: %s", orderDetailId));
        }

        return orderDetail;
    }

    @Override
    public OrderDetail deliverOrder(UUID customerId, UUID orderId, UUID orderDetailId) throws SeffafException {
        // bu kısım müşteri onayı ile oluyor, satıcıya para ödenebilir.
        Order order = orderService.getOrderByOrderId(orderId);
        if (order == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_FOUND, String.format("ORDER_NOT_FOUND: %s", orderId));
        }
        logger.info("order found: {}", order.getOrderId());

        if (!order.getDeliveredCustomerId().equals(customerId)) {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_FOUND, String.format("ORDER_NOT_FOUND: %s", orderId));
        }

        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        if (orderDetail == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_NOT_FOUND, String.format("ORDER_DETAILS_NOT_FOUND: %s", orderDetailId));
        }

        orderDetail.setOrderStatus(OrderStatus.DELIVERED);
        orderDetailService.save(orderDetail);
        logger.info("ORDER: {} APPROVED BY CUSTOMER: {}", orderDetail.getOrderDetailId(), orderDetail.getDeliveredCustomerId());
        return orderDetail;
    }

    @Transactional(rollbackFor = SeffafException.class)
    @Override
    public List<OrderDetail> cancelOrder(UUID orderId, Map<UUID, String> detailIds) throws SeffafException {

        List<OrderDetail> resultList = new ArrayList<>();
        Order order = orderService.getOrderByOrderId(orderId);
        if (order == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_FOUND, String.format("ORDER_NOT_FOUND: %s", orderId));
        }
        logger.info("order found: {}", order.getOrderId());

        for (Map.Entry<UUID, String> entry : detailIds.entrySet()) {
            UUID detailId = entry.getKey();
            String customerDescription = entry.getValue();

            OrderDetail orderDetail = orderDetailService.getOrderDetailById(detailId);
            if (orderDetail == null) {
                throw new SeffafException(SeffafExceptionCode.ORDER_DETAILS_NOT_FOUND, String.format("ORDER_DETAILS_NOT_FOUND: %s", detailId));
            } else {
                logger.info("cancel operation started for orderDetailId: {}", orderDetail.getOrderDetailId());
                cancelOrderDetail(orderDetail, customerDescription);
                resultList.add(orderDetail);
                logger.info("cancel operation finished for orderDetailId: {}", orderDetail.getOrderDetailId());
            }
        }
        return resultList;
    }

    private void cancelOrderDetail(OrderDetail orderDetail, String customerDescription) throws SeffafException {

        logger.info("orderDetail status: {}", orderDetail.getOrderStatus());
        // işlemler başarılıysa stok sayısını upgrade et..
        Product product = productService.getByProductId(orderDetail.getProductId());
        if (product == null) {
            throw new SeffafException(SeffafExceptionCode.PRODUCT_NOT_FOUND, String.format("PRODUCT_NOT_FOUND : %s", orderDetail.getProductId()));
        }

        /** eğer status IN_PAYMENT ise, status iptal yap, stok sayısını arttır */
        if (orderDetail.getOrderStatus() == OrderStatus.IN_PAYMENT) {
            orderDetail.setOrderStatus(OrderStatus.CANCELLED_IN_PAYMENT);
            orderDetailService.save(orderDetail);
            logger.info("orderDetail: {} status updated as CANCELLED_IN_PAYMENT!", orderDetail.getOrderDetailId());

            productService.increaseStockCountByProduct(product, orderDetail.getCount());
            logger.info("product: {} stock count increased +{}", product.getProductId(), orderDetail.getCount());

        } else if (Arrays.asList(OrderStatus.IN_QUEUE, OrderStatus.IN_PAYMENT).contains(orderDetail.getOrderStatus())) {
            orderDetail.setOrderStatus(OrderStatus.CANCELLED);
            orderDetailService.save(orderDetail);
            logger.info("orderDetail: {} status updated as CANCELLED!", orderDetail.getOrderDetailId());

            PaymentDetail paymentDetail = paymentDetailService.getPaymentDetailByOrderDetailId(orderDetail.getOrderDetailId());
            if (paymentDetail == null) {
                throw new SeffafException(SeffafExceptionCode.PAYMENT_DETAILS_NOT_FOUND, String.format("PAYMENT_DETAILS_NOT_FOUND for orderDetailId: %s", orderDetail.getOrderDetailId()));
            }
            paymentDetail.setPaymentFlow(PaymentFlow.TO_CUSTOMER); // ödeme iptal edildili için para transferi satıcıdan alıcıya döner
            paymentDetailService.save(paymentDetail);
            logger.info("paymentDetail: {} updated as TO_SELLER -> TO_CUSTOMER", paymentDetail.getPaymentDetailId());

            productService.increaseStockCountByProduct(product, orderDetail.getCount());
            logger.info("product: {} stock count increased +{}", product.getProductId(), orderDetail.getCount());
        } else if (orderDetail.getOrderStatus() == OrderStatus.IN_APPROVAL) {

            if (orderDetail.isRefunded()) {
                throw new SeffafException(SeffafExceptionCode.PRODUCT_CAN_NOT_BE_REFUNDED, String.format("PRODUCT_CAN_NOT_BE_REFUNDED : %s", orderDetail.getOrderDetailId()));
            }

            // paymentflow to_seller dan to_customer’a güncellenir
            PaymentDetail paymentDetail = paymentDetailService.getPaymentDetailByOrderDetailId(orderDetail.getOrderDetailId());
            if (paymentDetail == null) {
                throw new SeffafException(SeffafExceptionCode.PAYMENT_DETAILS_NOT_FOUND, String.format("PAYMENT_DETAILS_NOT_FOUND for orderDetailId: %s", orderDetail.getOrderDetailId()));
            }
            paymentDetail.setPaymentFlow(PaymentFlow.TO_CUSTOMER); // ödeme iptal edildili için para transferi satıcıdan alıcıya döner
            paymentDetailService.save(paymentDetail);
            logger.info("paymentDetail: {} updated as TO_SELLER -> TO_CUSTOMER", paymentDetail.getPaymentDetailId());

            //refunded tablosuna kayıt atılır (akış artık refunded status’le devam eder)
            RefundedDetail rDetail = new RefundedDetail();
            rDetail.setOrderDetailId(orderDetail.getOrderDetailId());
            rDetail.setProductId(orderDetail.getProductId());
            rDetail.setCount(orderDetail.getCount());
            rDetail.setProductAmount(orderDetail.getProductAmount());
            rDetail.setCurrency(orderDetail.getCurrency());
            rDetail.setSellerCustomerId(orderDetail.getSellerCustomerId());
            rDetail.setSellerAddressId(orderDetail.getSellerAddressId());
            rDetail.setDeliveredCustomerId(orderDetail.getDeliveredCustomerId());
            rDetail.setDeliveryAddressId(orderDetail.getDeliveryAddressId());
            rDetail.setCustomerDescription(customerDescription);
            rDetail.setRefundedStatus(RefundedStatus.WAITING_FOR_TRANSPORT);
            refundedService.save(rDetail);
            logger.info("refundedDetail created: {}", rDetail.getRefundedDetailId());


            // orderDetail’e refundedId eklenir.
            orderDetail.setOrderStatus(OrderStatus.REFUNDED);
            orderDetail.setRefundedDetailId(rDetail.getRefundedDetailId());
            orderDetailService.save(orderDetail);
            logger.info("orderDetail: {} status updated as REFUNDED!", orderDetail.getOrderDetailId());
        } else {
            throw new SeffafException(SeffafExceptionCode.ORDER_NOT_IN_CANCEL_STAGE, String.format("ORDER_NOT_IN_CANCEL_STAGE orderDetailId: %s", orderDetail.getOrderDetailId()));
        }
    }

    @Override
    public RefundedDetail getRefundedOrder(UUID refundedDetailId) throws SeffafException {
        RefundedDetail rDetail = refundedService.getRefundedById(refundedDetailId);
        if (rDetail == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_REFUNDED_NOT_FOUND, String.format("ORDER_REFUNDED_NOT_FOUND : %s", refundedDetailId));
        }
        logger.info("refundedDetail found: {}", rDetail.getRefundedDetailId());
        return rDetail;
    }

    @Override
    public RefundedDetail transportRefunded(UUID refundedDetailId, String trackingNumber) throws SeffafException {

        RefundedDetail rDetail = refundedService.getRefundedById(refundedDetailId);
        if (rDetail == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_REFUNDED_NOT_FOUND, String.format("ORDER_REFUNDED_NOT_FOUND : %s", refundedDetailId));
        }
        logger.info("refundedDetail found: {}", rDetail.getRefundedDetailId());

        if (rDetail.getRefundedStatus() == RefundedStatus.WAITING_FOR_TRANSPORT) {
            rDetail.setTrackingNumber(trackingNumber);
            rDetail.setShippedDate(new Date());
            rDetail.setRefundedStatus(RefundedStatus.IN_TRANSPORT);
            refundedService.save(rDetail);
            logger.info("refundedDetail updated: {} with trackingNumber: {}", rDetail.getRefundedDetailId(), trackingNumber);
        } else {
            throw new SeffafException(SeffafExceptionCode.UNIMPLEMENTED_METHOD, String.format("UNIMPLEMENTED_METHOD : %s", refundedDetailId));
        }

        return rDetail;
    }

    @Override
    public RefundedDetail deliverRefunded(UUID sellerCustomerId, UUID refundedDetailId, RefundedStatus refundedStatus, String sellerDescription) throws SeffafException {
        RefundedDetail rDetail = refundedService.getRefundedById(refundedDetailId);
        if (rDetail == null) {
            throw new SeffafException(SeffafExceptionCode.ORDER_REFUNDED_NOT_FOUND, String.format("ORDER_REFUNDED_NOT_FOUND : %s", refundedDetailId));
        }
        logger.info("refundedDetail found: {}", rDetail.getRefundedDetailId());

        if (!rDetail.getSellerCustomerId().equals(sellerCustomerId)) {
            throw new SeffafException(SeffafExceptionCode.ORDER_REFUNDED_NOT_FOUND, String.format("ORDER_REFUNDED_NOT_FOUND : %s", refundedDetailId));
        }

        // TODO: 4/21/19 bu kısım kargo entegrasyonuna göre değişecek
        if (rDetail.getRefundedStatus() != RefundedStatus.IN_TRANSPORT) {
            throw new SeffafException(SeffafExceptionCode.ORDER_UNKNOWN_STAGE, String.format("ORDER_UNKNOWN_STAGE: %s", refundedStatus.name()));
        }

        if (refundedStatus == RefundedStatus.DELIVERED) {
            rDetail.setReceivedDate(new Date());
            rDetail.setRefundedStatus(RefundedStatus.DELIVERED);
            refundedService.save(rDetail);
            logger.info("refunded accepted by seller customer!");

            Product product = productService.getByProductId(rDetail.getProductId());
            productService.increaseStockCountByProduct(product, rDetail.getCount());
            logger.info("product: {} stock count increased +{}", product.getProductId(), rDetail.getCount());

        } else if (refundedStatus == RefundedStatus.REFUSED) {
            rDetail.setReceivedDate(new Date());
            rDetail.setRefundedStatus(RefundedStatus.REFUSED);
            rDetail.setSellerDescription(sellerDescription);
            refundedService.save(rDetail);
            // TODO: 4/21/19 bu kısımda mail at alarm üret vs vs...
            logger.info("refunded REFUSED by seller customer!");
        } else {
            throw new SeffafException(SeffafExceptionCode.UNIMPLEMENTED_METHOD, String.format("UNIMPLEMENTED_METHOD: %s", refundedStatus.name()));
        }

        return rDetail;
    }
}
