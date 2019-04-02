package com.payment.seffaf.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class SeffafException extends Exception {

    int code = -1;
    private Object[] params;

    public SeffafException(Throwable e) {
        super(e);
    }

    public SeffafException(int code) {
        super();
        this.code = code;
    }

    public SeffafException(int code, Object[] params) {
        super();
        this.code = code;
        this.params = params;
    }

    public SeffafException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }


    public String getMessage() {
        if (null == super.getMessage()) {
            return Arrays.asList(this.getParams()).toString();
        } else {
            return super.getMessage();
        }
    }

    public static String parseExceptionMessage(Exception exception, int mLength) {
        if (exception == null) {
            return "";
        } else {
            if (exception.getMessage() == null || exception.getMessage().isEmpty()) {
                StringWriter sw = new StringWriter();
                exception.printStackTrace(new PrintWriter(sw));
                int length = (sw.toString().length() > 1000) ? mLength : sw.toString().length();
                return sw.toString().substring(0, length);
            } else {
                int length = (exception.getMessage().length() > 1000) ? mLength : exception.getMessage().length();
                return exception.getMessage().substring(0, length);
            }
        }
    }
}
