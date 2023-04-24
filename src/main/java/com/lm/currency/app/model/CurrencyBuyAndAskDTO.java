package com.lm.currency.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CurrencyBuyAndAskDTO {
    private String table;
    private String currency;
    private String code;
    private List<CurrencyBuyAndAskRatesDTO> rates;
}