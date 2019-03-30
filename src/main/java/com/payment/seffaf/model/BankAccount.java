package com.payment.seffaf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * enbiya on 24.03.2019
 */
@Entity
@Table(name = "t_bank_account")
public class BankAccount extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_holdername", nullable = false)
    private String cardHolderName;

    @Column(name = "iban_number", nullable = false)
    private String ibanNumber;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getIbanNumber() {
        return ibanNumber;
    }

    public void setIbanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }

}
