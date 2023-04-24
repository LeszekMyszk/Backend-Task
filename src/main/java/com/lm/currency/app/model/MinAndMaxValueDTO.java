package com.lm.currency.app.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MinAndMaxValueDTO {
    private double minExchangeRateValue;
    private double maxExchangeRateValue;
}



//TODO delete comments below
//        "table":"A",
//        "currency":"dolar amerykański",
//        "code":"USD",
//        "rates":[
//            {"no":"077/A/NBP/2023",
//            "effectiveDate":"2023-04-20",
//            "mid":4.2024},
//            {"no":"078/A/NBP/2023",
//            "effectiveDate":"2023-04-21",
//            "mid":4.2006}
//            ]

