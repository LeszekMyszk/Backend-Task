package com.lm.currency.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.model.MinAndMaxValueDTO;
import com.lm.currency.app.webclient.NBPClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrencyService {

    private final NBPClient nbpClient;

    public CurrencyService(NBPClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public CurrencyValueDTO getCurrencyValueAtDate(String code, LocalDate date) throws JsonProcessingException {
        // TODO ADD SANITIZE / VERIFY (normal or bean validation)
        return nbpClient.getCurrencyValue(code, date);
    }

    public String getMinAndMaxValueFromNQuotations(String code, int topCount) {
        // TODO ADD SANITIZE / VERIFY (normal or bean validation)
        return nbpClient.getMinAndMaxValue(code, topCount);
    }

    public String getMaxDifferenceBetweenBuyAndAsk(String code, int topCount) {
        // TODO ADD SANITIZE / VERIFY (normal or bean validation)
        return nbpClient.getMaxDifference(code, topCount);
    }
}

/*
    Provide a separate endpoint for each operation:

        1) Given a date (formatted YYYY-MM-DD) and a currency code (list: https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/), provide its average exchange rate.
            URL: "http://api.nbp.pl/api/exchangerates/rates/a/gbp/2012-01-02/"
            return: value


        2) Given a currency code and the number of last quotations N (N <= 255), provide the max and min average value (every day has a different average).
            URL: "http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/last/{topCount}/"
            validation: N <= 255
            return: min and max value


        3) Given a currency code and the number of last quotations N (N <= 255), provide the major difference between the buy and ask rate (every day has different rates).
            URL: "http://api.nbp.pl/api/exchangerates/rates/c/usd/last/10/?format=json"
            validation: N <= 255
            check differences between bid and ask
            return: max difference
 */