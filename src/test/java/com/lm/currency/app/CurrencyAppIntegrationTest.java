package com.lm.currency.app;


import com.lm.currency.app.exceptions.InvalidDateException;
import com.lm.currency.app.model.CurrencyAverageDTO;
import com.lm.currency.app.model.CurrencyAverageRatesDTO;
import com.lm.currency.app.model.CurrencyBuyAndAskRatesDTO;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.service.CurrencyService;
import com.lm.currency.app.webclient.NBPClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Currency;

import static org.mockito.Mockito.when;


@SpringBootTest
public class CurrencyAppIntegrationTest {

    Currency correctCurrency = Currency.getInstance("USD");
    LocalDate correctLocalDate = LocalDate.now().minusDays(10);

    @MockBean
    NBPClient nbpClientMock;
    @Autowired
    CurrencyService currencyService;
    @Test
    void whenGetValueAtDateThenResponseRate() throws InvalidDateException {
        //given
        CurrencyAverageDTO currencyAverageDTO = new CurrencyAverageDTO();
        CurrencyAverageRatesDTO rate = new CurrencyAverageRatesDTO();
        rate.setMid(1.234);
        currencyAverageDTO.setRates(Collections.singletonList(rate));
        when(nbpClientMock.getCurrencyValue(correctCurrency,correctLocalDate)).thenReturn(currencyAverageDTO);

        //when
        CurrencyValueDTO currencyValueDTO = currencyService.getCurrencyValueAtDate(correctCurrency, correctLocalDate);

        //then
        Assertions.assertThat(currencyValueDTO).isNotNull();
        Assertions.assertThat(currencyValueDTO.getAverageExchangeRate()).isNotNull();
    }


}
