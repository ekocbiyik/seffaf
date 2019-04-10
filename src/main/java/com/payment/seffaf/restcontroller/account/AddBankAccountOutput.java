package com.payment.seffaf.restcontroller.account;

import com.payment.seffaf.model.BankAccount;

/**
 * ekocbiyik on 4/10/19
 */
public class AddBankAccountOutput {

    private int responseCode;
    private BankAccount bankAccount;

    public AddBankAccountOutput(int responseCode, BankAccount bankAccount) {
        this.responseCode = responseCode;
        this.bankAccount = bankAccount;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
