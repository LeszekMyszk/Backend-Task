package com.lm.currency.app.model;

public class CurrencyValueDTO {
    double averageExchangeRate;

    public CurrencyValueDTO(){

    };
    public CurrencyValueDTO(double averageExchangeRate) {
        this.averageExchangeRate = averageExchangeRate;
    }

    public double getAverageExchangeRate() {
        return averageExchangeRate;
    }

    public CurrencyValueDTO setAverageExchangeRate(double averageExchangeRate) {
        this.averageExchangeRate = averageExchangeRate;
        return null;
    }
}
