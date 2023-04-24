package com.lm.currency.app.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MaxDifferenceDTO {
    private double maxDifferenceBuyAndAskRate;
}

