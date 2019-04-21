package com.payment.seffaf.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Entity
@Table(name = "t_order_detail")
@Audited
@EntityListeners({AuditingEntityListener.class})
public class OrderDetail extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "order_detail_id", nullable = false, unique = true)
    private UUID orderDetailId;

    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "is_refunded", nullable = false) // iade edilebilir mi?
    private boolean isRefunded;

    @Column(name = "refunded_detail_id")
    private UUID refundedDetailId;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "description")
    private String description;

    @Column(name = "product_amount", nullable = false)
    private BigDecimal productAmount;

    @Column(name = "transport_amount", nullable = false)
    private BigDecimal transportAmount;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "seller_customer_id", nullable = false)
    private UUID sellerCustomerId;

    @Column(name = "delivered_customer_id", nullable = false)
    private UUID deliveredCustomerId;

    @Column(name = "seller_address_id", nullable = false)
    private UUID sellerAddressId;

    @Column(name = "delivery_address_id", nullable = false)
    private UUID deliveryAddressId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "shipped_date")
    private Date shippedDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "received_date")
    private Date receivedDate;

    public UUID getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(UUID orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    public UUID getRefundedDetailId() {
        return refundedDetailId;
    }

    public void setRefundedDetailId(UUID refundedDetailId) {
        this.refundedDetailId = refundedDetailId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(BigDecimal transportAmount) {
        this.transportAmount = transportAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public UUID getSellerCustomerId() {
        return sellerCustomerId;
    }

    public void setSellerCustomerId(UUID sellerCustomerId) {
        this.sellerCustomerId = sellerCustomerId;
    }

    public UUID getDeliveredCustomerId() {
        return deliveredCustomerId;
    }

    public void setDeliveredCustomerId(UUID deliveredCustomerId) {
        this.deliveredCustomerId = deliveredCustomerId;
    }

    public UUID getSellerAddressId() {
        return sellerAddressId;
    }

    public void setSellerAddressId(UUID sellerAddressId) {
        this.sellerAddressId = sellerAddressId;
    }

    public UUID getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(UUID deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
}
