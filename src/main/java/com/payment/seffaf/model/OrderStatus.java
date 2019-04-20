package com.payment.seffaf.model;

/**
 * enbiya on 24.03.2019
 */
public enum OrderStatus {

    IN_PAYMENT,             // siparişi oluşturdu, henüz ödeme yapmadı
    CANCELLED_IN_PAYMENT,   // ödeme yapılmadan iptal edildi
    IN_QUEUE,               // ödeme yaptı, satıcı onayı bekliyor
    IN_PREPARE,             // satıcı onayladı, hazırlanıyor
    IN_TRANSPORT,           // kargoda
    IN_APPROVAL,            // kargoyu aldı, onayda
    DELIVERED,              // teslim edildi
    CANCELLED,              // ödeme yapıldıktan sonra iptal edildi
    REFUNDED                // geri iade talebi

}
