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
 * ekocbiyik on 4/20/19
 */
@Entity
@Table(name = "t_refunded_detail")
@Audited
@EntityListeners({AuditingEntityListener.class})
public class RefundedDetail extends Auditable {

    // bu tablodaki akış orderın tam tersi, fakat fieldlar gerçek anlamlarına sahip, seller-> ürünü ilk satan kişi. ama ürün deliveredCustomerdan sellerCustomera gidiyor.

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "refunded_detail_id", nullable = false, unique = true)
    private UUID refundedDetailId;

    @Column(name = "order_detail_id", nullable = false, unique = true)
    private UUID orderDetailId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "product_amount", nullable = false)
    private BigDecimal productAmount;

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

    @Column(name = "customer_description")
    private String customerDescription; // satan kişi, geri gelen ürüne neden itiraz etti?

    @Column(name = "seller_description")
    private String sellerDescription; // alan kişi niye itiraz etti?

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "refunded_status", nullable = false)
    private RefundedStatus refundedStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "shipped_date")
    private Date shippedDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "received_date")
    private Date receivedDate;

    public UUID getRefundedDetailId() {
        return refundedDetailId;
    }

    public void setRefundedDetailId(UUID refundedDetailId) {
        this.refundedDetailId = refundedDetailId;
    }

    public UUID getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(UUID orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
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

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }

    public String getSellerDescription() {
        return sellerDescription;
    }

    public void setSellerDescription(String sellerDescription) {
        this.sellerDescription = sellerDescription;
    }

    public RefundedStatus getRefundedStatus() {
        return refundedStatus;
    }

    public void setRefundedStatus(RefundedStatus refundedStatus) {
        this.refundedStatus = refundedStatus;
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
