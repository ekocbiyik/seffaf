package com.payment.seffaf.exceptions;

/**
 * enbiya on 02.04.2019
 */
public class SeffafExceptionCode {

    public static final int INVALID_PARAMETER = 300;
    public static final int REQUIRED_PARAMETER = 301;
    public static final int UNIMPLEMENTED_METHOD = 302;

    //customer
    public static final int CUSTOMER_NOT_FOUND = 400;

    //address
    public static final int ADDRESS_NOT_FOUND = 500;

    //bankAccount
    public static final int BANK_ACCOUNT_NOT_FOUND = 600;

    //product
    public static final int PRODUCT_NOT_FOUND = 700;
    public static final int PRODUCT_LIST_CAN_NOT_BE_EMPTY = 701;
    public static final int PRODUCT_STOCK_NOT_ENOUGH = 702;
    public static final int PRODUCT_COUNT_MUST_BE_DIFFERENT_ZERO = 703;

    //order
    public static final int ORDER_NOT_FOUND = 800;
    public static final int ORDER_DETAILS_NOT_FOUND = 801;

}
