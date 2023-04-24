package com.lm.currency.app.webclient;

import com.lm.currency.app.model.CurrencyAverageDTO;
import com.lm.currency.app.model.CurrencyBuyAndAskDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Currency;

@Component
public class NBPClient {

    private static final String HTTP_NBP_URL = "http://api.nbp.pl/api/exchangerates/rates";

    private final RestTemplate restTemplate = new RestTemplate();


    public CurrencyAverageDTO getCurrencyValue(Currency code, LocalDate date) {
        return callGetMethod("/a/{code}/{date}/",
                CurrencyAverageDTO.class,
                code, date);
    }


    public CurrencyAverageDTO getMinAndMaxAverageValue(Currency code, int topCount) {
        return callGetMethod("/a/{code}/last/{topCount}/",
                CurrencyAverageDTO.class,
                code, topCount);
    }


    public CurrencyBuyAndAskDTO getMaxDifference(Currency code, int topCount) {
        return callGetMethod("/c/{code}/last/{topCount}/",
                CurrencyBuyAndAskDTO.class,
                code, topCount); // TODO EXCEPTION HANDLING, RESPONSE OPTIONAL OR THROW ERROR
    }


    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(HTTP_NBP_URL + url, responseType, objects);
    }


}
