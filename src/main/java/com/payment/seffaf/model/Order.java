package com.payment.seffaf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Entity
@Table(name = "t_order")
public class Order extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId;

    @Column(name = "delivered_customer_id", nullable = false)
    private UUID deliveredCustomerId;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getDeliveredCustomerId() {
        return deliveredCustomerId;
    }

    public void setDeliveredCustomerId(UUID deliveredCustomerId) {
        this.deliveredCustomerId = deliveredCustomerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
