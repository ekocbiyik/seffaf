package com.payment.seffaf.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "t_payment")
public class Payment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "payment_id")
    private UUID paymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_number", nullable = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount bankAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Customer sellerCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivered_id", nullable = false)
    private Customer deliveredCustomer;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "payment_amount", nullable = false)
    private BigDecimal paymentAmount;

    @Column(name = "vpos_code")
    private String vposCode;

    @Column(name = "vpos_ref_code")
    private String vposRefCode;

    @Column(name = "id_3d")
    private boolean is3D;

    @Column(name = "bank_code")
    private String bankCode;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getVposCode() {
        return vposCode;
    }

    public void setVposCode(String vposCode) {
        this.vposCode = vposCode;
    }

    public String getVposRefCode() {
        return vposRefCode;
    }

    public void setVposRefCode(String vposRefCode) {
        this.vposRefCode = vposRefCode;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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
