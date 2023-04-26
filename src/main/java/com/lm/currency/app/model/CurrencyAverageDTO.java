package com.lm.currency.app.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class CurrencyAverageDTO {
    private String table;
    private String currency;
    private String code;
    private List<CurrencyAverageRatesDTO> rates;

}
