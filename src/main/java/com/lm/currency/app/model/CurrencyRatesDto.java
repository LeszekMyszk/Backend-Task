package com.lm.currency.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CurrencyRatesDto {
    @JsonProperty("no")
    private String no;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("mid")
    private double mid;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public double getMid() {
        return mid;
    }

    //    public double getMid() {
//        return mid;
//    }


}
