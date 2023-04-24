package com.lm.currency.app.service;

import com.lm.currency.app.exceptions.InvalidAmountOfQuotationsException;
import com.lm.currency.app.exceptions.InvalidDateException;
import com.lm.currency.app.model.*;
import com.lm.currency.app.webclient.NBPClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

@Service
public class CurrencyService {

    private final NBPClient nbpClient;

    public CurrencyService(NBPClient nbpClient) {
        this.nbpClient = nbpClient;
    }


    public CurrencyValueDTO getCurrencyValueAtDate(Currency code, LocalDate date) throws InvalidDateException {
        if (date.plusDays(1).isAfter(LocalDate.now()) || date.plusYears(2).isBefore(LocalDate.now())) {
            throw new InvalidDateException("You can access exchange rates in period from two years ago until yesterday.");
        }
        CurrencyAverageDTO currencyAverageDTO = nbpClient.getCurrencyValue(code, date);
        List<Double> averageRateList = currencyAverageDTO.getRates().stream().map(CurrencyAverageRatesDTO::getMid).toList();
        return CurrencyValueDTO.builder().averageExchangeRate(averageRateList.get(0)).build();
    }


    public MinAndMaxValueDTO getMinAndMaxValueFromNQuotations(Currency code, int topCount) throws InvalidAmountOfQuotationsException {
        if (topCount > 255) {
            throw new InvalidAmountOfQuotationsException("Max amount of quotations is 255.");
        }
        CurrencyAverageDTO currencyAverageDTO = nbpClient.getMinAndMaxAverageValue(code, topCount);
        List<Double> averageRateList = currencyAverageDTO.getRates().stream().map(CurrencyAverageRatesDTO::getMid).toList();
        Double minValue = Collections.min(averageRateList);
        Double maxValue = Collections.max(averageRateList);
        return MinAndMaxValueDTO.builder().minExchangeRateValue(minValue).maxExchangeRateValue(maxValue).build();
    }


    public MaxDifferenceDTO getMaxDifferenceBetweenBuyAndAsk(Currency code, int topCount) throws InvalidAmountOfQuotationsException {
        if (topCount > 255) {
            throw new InvalidAmountOfQuotationsException("Max amount of quotations is 255.");
        }
        CurrencyBuyAndAskDTO currencyBuyAndAskDTO = nbpClient.getMaxDifference(code, topCount);
        List<Double> askRateList = currencyBuyAndAskDTO.getRates().stream().map(CurrencyBuyAndAskRatesDTO::getAsk).toList();
        List<Double> bidRateList = currencyBuyAndAskDTO.getRates().stream().map(CurrencyBuyAndAskRatesDTO::getBid).toList();
        List<Double> differenceBidAndAskRateList = new ArrayList<>();
        for (int i = 0; i < askRateList.size(); i++) {
            differenceBidAndAskRateList.add((askRateList.get(i) - bidRateList.get(i)));
        }
        BigDecimal maxDifference = BigDecimal.valueOf(Collections.max(differenceBidAndAskRateList)).setScale(4, RoundingMode.HALF_UP);
        double maxDifferenceBuyAndAsk = maxDifference.doubleValue();
        return MaxDifferenceDTO.builder()
                .maxDifferenceBuyAndAskRate(maxDifferenceBuyAndAsk)
                .build();
    }
}
