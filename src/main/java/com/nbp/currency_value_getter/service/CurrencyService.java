package com.nbp.currency_value_getter.service;

import com.nbp.currency_value_getter.model.CurrencyDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    private RestTemplate restTemplate = new RestTemplate();
    public CurrencyService(){

    };
    public CurrencyDto getCurrencyValueAtDate() {
        String response = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/gbp/2012-01-02/", String.class);
        System.out.println(response);
        return null;
    }
}
