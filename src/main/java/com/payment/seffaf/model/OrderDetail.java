package com.payment.seffaf.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_detail_id", nullable = false)
    private Integer orderDetailId;

    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @Column(name = "refunded_number")
    private Integer refundedNumber;

    @Column(name = "product_number", nullable = false)
    private UUID productNumber;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_customer", nullable = false)
    private Customer sellerCustomer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivered_customer", nullable = false)
    private Customer deliveredCustomer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_address", nullable = false)
    private Address sellerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address", nullable = false)
    private Address deliveryAddress;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "shipped_date")
    private Date shippedDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "received_date")
    private Date receivedDate;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getRefundedNumber() {
        return refundedNumber;
    }

    public void setRefundedNumber(Integer refundedNumber) {
        this.refundedNumber = refundedNumber;
    }

    public UUID getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(UUID productNumber) {
        this.productNumber = productNumber;
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

    public Customer getSellerCustomer() {
        return sellerCustomer;
    }

    public void setSellerCustomer(Customer sellerCustomer) {
        this.sellerCustomer = sellerCustomer;
    }

    public Customer getDeliveredCustomer() {
        return deliveredCustomer;
    }

    public void setDeliveredCustomer(Customer deliveredCustomer) {
        this.deliveredCustomer = deliveredCustomer;
    }

    public Address getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(Address sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
