package com.lm.currency.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CurrencyBuyAndAskDTO {
    @JsonProperty("table")
    private String table;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("code")
    private String code;
    @JsonProperty("rates")
    private List<CurrencyBuyAndAskRatesDTO> rates;

}

//TODO delete comments below
//      { "table":"A",
//        "currency":"funt szterling",
//        "code":"GBP",
//        "rates":[{"no":"075/A/NBP/2023",
//                "effectiveDate":"2023-04-18",
//                "mid":5.2428}]
//      }