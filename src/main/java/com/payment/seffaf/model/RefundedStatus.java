package com.payment.seffaf.model;

/**
 * ekocbiyik on 4/20/19
 */
public enum RefundedStatus {

    WAITING_FOR_TRANSPORT,  // müşteri geri iade için kargoya veriyor
    IN_TRANSPORT,           // kargoda
    IN_APPROVAL,            // kargoyu aldı, onayda
    DELIVERED,              // alıcıya ulaştı
    REFUSED                 // alıcı reddedti

}
