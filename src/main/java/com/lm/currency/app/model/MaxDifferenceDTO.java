package com.lm.currency.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MaxDifferenceDTO {
    private double maxDifferenceBuyAndAskRate;
}



//TODO delete comments below
//{
//"table":"C",
//"currency":"dolar amerykański",
//"code":"USD",
//"rates":[
//    {"no":"077/C/NBP/2023",
//    "effectiveDate":"2023-04-20",
//    "bid":4.1677,
//    "ask":4.2519},
//
//    {"no":"078/C/NBP/2023",
//    "effectiveDate":"2023-04-21",
//    "bid":4.1532,
//    "ask":4.2372}
//    ]
//}
