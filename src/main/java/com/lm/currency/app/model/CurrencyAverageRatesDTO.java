package com.lm.currency.app.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrencyAverageRatesDTO {
    private String no;
    private String effectiveDate;
    private double mid;

    public CurrencyAverageRatesDTO(double v) {
    }
}
