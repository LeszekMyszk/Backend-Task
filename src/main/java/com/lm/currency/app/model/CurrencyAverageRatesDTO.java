package com.lm.currency.app.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CurrencyAverageRatesDTO {
    private String no;
    private String effectiveDate;
    private double mid;

}
