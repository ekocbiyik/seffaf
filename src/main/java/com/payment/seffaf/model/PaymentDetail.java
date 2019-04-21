package com.payment.seffaf.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

/**
 * ekocbiyik on 4/20/19
 */
@Entity
@Table(name = "t_payment_detail")
@Audited
@EntityListeners({AuditingEntityListener.class})
public class PaymentDetail extends Auditable{
    /**
     * bu tablo yapılacak olan ödemeleirn bilgileirni tutmak için
     */

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "payment_detail_id")
    private UUID paymentDetailId;

    @Column(name = "payment_id", nullable = false)
    private UUID paymentId;

    @Column(name = "order_detail_id", nullable = false)
    private UUID orderDetailId;

    @Column(name = "seller_customer_id", nullable = false)
    private UUID sellerCustomerId;

    @Column(name = "delivered_customer_id", nullable = false)
    private UUID deliveredCustomerId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_flow", nullable = false)
    private PaymentFlow paymentFlow;                  // ödeme kimden kime yapılıyor

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;                  // para sistemde mi, iade edildi mi?

    public UUID getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(UUID paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(UUID orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public PaymentFlow getPaymentFlow() {
        return paymentFlow;
    }

    public void setPaymentFlow(PaymentFlow paymentFlow) {
        this.paymentFlow = paymentFlow;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
