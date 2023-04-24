package com.lm.currency.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyAverageDTO {
    private String table;
    private String currency;
    private String code;
    private List<CurrencyAverageRatesDTO> rates;

    public CurrencyAverageDTO(List<CurrencyAverageRatesDTO> rates) {
    }
}

//TODO delete comments below
//      { "table":"A",
//        "currency":"funt szterling",
//        "code":"GBP",
//        "rates":[{"no":"075/A/NBP/2023",
//                "effectiveDate":"2023-04-18",
//                "mid":5.2428}]
//      }