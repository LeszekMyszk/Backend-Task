package com.lm.currency.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CurrencyDTO {
    @JsonProperty("table")
    private String table;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rates")
    private List<CurrencyRatesDto> rates;

    public List<CurrencyRatesDto> getRates() {
        return rates;
    }
}

//}
//      { "table":"A",
//        "currency":"funt szterling",
//        "code":"GBP",
//        "rates":[{"no":"075/A/NBP/2023",
//                "effectiveDate":"2023-04-18",
//                "mid":5.2428}]
//      }