package com.payment.seffaf.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Entity
@Table(name = "t_product")
public class Product extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "product_amount", nullable = false)
    private BigDecimal productAmount;

    @Column(name = "transport_amount", nullable = false)
    private BigDecimal transportAmount;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "stock_count", nullable = false)
    private int stockCount;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "estimate_date")
    private int estimateDay;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimateDay() {
        return estimateDay;
    }

    public void setEstimateDay(int estimateDay) {
        this.estimateDay = estimateDay;
    }

}
