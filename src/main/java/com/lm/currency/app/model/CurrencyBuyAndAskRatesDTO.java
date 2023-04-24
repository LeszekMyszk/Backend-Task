package com.lm.currency.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CurrencyBuyAndAskRatesDTO {
    private String no;
    private String effectiveDate;
    private double bid;
    private double ask;
}
