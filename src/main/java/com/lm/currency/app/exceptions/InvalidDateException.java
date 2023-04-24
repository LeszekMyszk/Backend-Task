package com.lm.currency.app.exceptions;

public class InvalidDateException extends Throwable {
    private final String errorInfo;

    public InvalidDateException(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}

