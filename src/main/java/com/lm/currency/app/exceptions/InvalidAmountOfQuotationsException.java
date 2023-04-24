package com.lm.currency.app.exceptions;

public class InvalidAmountOfQuotationsException extends RuntimeException {
    private final String errorInfo;
    public InvalidAmountOfQuotationsException(String errorInfo) {
        this.errorInfo = errorInfo;
    }
    public String getErrorInfo(){
        return errorInfo;
    }
}
