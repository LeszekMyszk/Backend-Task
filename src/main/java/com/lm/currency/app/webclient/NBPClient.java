package com.lm.currency.app.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lm.currency.app.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class NBPClient {

    private String url = "{HTTP_NBP_URL}/{table}/{code}/{date}/";
    private static final String HTTP_NBP_URL = "http://api.nbp.pl/api/exchangerates/rates";

    private RestTemplate restTemplate = new RestTemplate();


    public CurrencyValueDTO getCurrencyValue(String code, LocalDate date) throws JsonProcessingException {
        String jsonCurrency = callGetMethod("/a/{code}/{date}/",
                String.class,
                code, date); // TODO EXCEPTION HANDLING, RESPONSE OPTIONAL OR THROW ERROR

        System.out.println(jsonCurrency);
        ObjectMapper mapper = new ObjectMapper();
        CurrencyAverageDTO currencyAverageDTO = mapper.readValue(jsonCurrency, CurrencyAverageDTO.class);
        return new CurrencyValueDTO(currencyAverageDTO.getRates().get(0).getMid());
    }

    public MinAndMaxValueDTO getMinAndMaxValue(String code, int topCount) {
        CurrencyAverageDTO currencyAverageDTO = callGetMethod("/a/{code}/last/{topCount}/",
                CurrencyAverageDTO.class,
                code, topCount); // TODO EXCEPTION HANDLING, RESPONSE OPTIONAL OR THROW ERROR
        List<CurrencyAverageRatesDTO> currencyAverageRatesDTOList = currencyAverageDTO.getRates();
        List<Double> averageRateList = currencyAverageRatesDTOList.stream().map(CurrencyAverageRatesDTO::getMid).toList();
        Double minValue = Collections.min(averageRateList);
        Double maxValue = Collections.max(averageRateList);
        return MinAndMaxValueDTO.builder()
                .minExchangeRateValue(minValue)
                .maxExchangeRateValue(maxValue)
                .build();
    }


    public MaxDifferenceDTO getMaxDifference(String code, int topCount) {
        CurrencyBuyAndAskDTO currencyBuyAndAskDTO = callGetMethod("/c/{code}/last/{topCount}/",
                CurrencyBuyAndAskDTO.class,
                code, topCount); // TODO EXCEPTION HANDLING, RESPONSE OPTIONAL OR THROW ERROR
        List<CurrencyBuyAndAskRatesDTO> currencyBuyAndAskRatesDTOList = currencyBuyAndAskDTO.getRates();
        List<Double> askRateList = currencyBuyAndAskRatesDTOList.stream().map(CurrencyBuyAndAskRatesDTO::getAsk).toList();
        List<Double> bidRateList = currencyBuyAndAskRatesDTOList.stream().map(CurrencyBuyAndAskRatesDTO::getBid).toList();
        List<Double> differenceBidAndAskRateList = new ArrayList<>();
        for (int i = 0; i < askRateList.size(); i++) {
            differenceBidAndAskRateList.add(Math.round((askRateList.get(i) - bidRateList.get(i)) * 10000.0) / 10000.0);
        }
        System.out.println(askRateList);
        System.out.println(bidRateList);
        System.out.println(differenceBidAndAskRateList);
        Double maxDifference = Collections.max(differenceBidAndAskRateList);
        return MaxDifferenceDTO.builder()
                .maxDifferenceBuyAndAskRate(maxDifference)
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(HTTP_NBP_URL + url, responseType, objects);
    }


}
