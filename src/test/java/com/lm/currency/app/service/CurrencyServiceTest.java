package com.lm.currency.app.service;


import com.lm.currency.app.configuration.TimeService;
import com.lm.currency.app.exceptions.InvalidAmountOfQuotationsException;
import com.lm.currency.app.exceptions.InvalidDateException;
import com.lm.currency.app.model.CurrencyAverageDTO;
import com.lm.currency.app.model.CurrencyAverageRatesDTO;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.model.MinAndMaxValueDTO;
import com.lm.currency.app.webclient.NBPClient;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@WebMvcTest(CurrencyService.class)
class CurrencyServiceTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    NBPClient nbpClient;
    @MockBean
    TimeService timeService;


    CurrencyService underTest;
    Currency correctCurrency = Currency.getInstance("USD");
    LocalDate correctDate = LocalDate.now().minusYears(1);
    LocalDate incorrectDate = LocalDate.now().minusYears(3);
    List<CurrencyAverageRatesDTO> correctRates = new ArrayList<>();
    CurrencyAverageDTO currencyAverageDTO = new CurrencyAverageDTO();
    int correctTopCount = 100;
    int incorrectTopCount = 300;

    @BeforeEach
    void prepare() {
        underTest = new CurrencyService(nbpClient);
        correctRates.add(new CurrencyAverageRatesDTO("a", correctDate.toString(), 1.2345));
        currencyAverageDTO.setRates(correctRates);

    }


    @Test
    void getCurrencyValueAtCorrectDate() throws Exception, InvalidDateException {
        //given
        BDDMockito.given(nbpClient.getCurrencyValue(correctCurrency, correctDate)).willReturn(currencyAverageDTO);

        //when
        CurrencyValueDTO result = underTest.getCurrencyValueAtDate(correctCurrency, correctDate);

        //then
        Assertions.assertThat(result).isNotNull()
                .isInstanceOf(CurrencyValueDTO.class)
                .hasFieldOrProperty("averageExchangeRate");
    }


    @Test
    void getCurrencyValueAtIncorrectDate() throws Exception, InvalidDateException {
        //given
        BDDMockito.given(nbpClient.getCurrencyValue(correctCurrency, incorrectDate)).willReturn(currencyAverageDTO);

        //when
        ThrowableAssert.ThrowingCallable result = () -> underTest.getCurrencyValueAtDate(correctCurrency, incorrectDate);

        //then
        Assertions.assertThatException().getClass().equals(InvalidDateException.class);
    }

    @Test
    void getCurrencyMinAndMaxValueFromNCorrectQuotations() throws Exception, InvalidAmountOfQuotationsException {
        //given
        BDDMockito.given(nbpClient.getMinAndMaxAverageValue(correctCurrency, correctTopCount)).willReturn(currencyAverageDTO);

        //when
        MinAndMaxValueDTO result = underTest.getMinAndMaxValueFromNQuotations(correctCurrency, correctTopCount);

        //then
        Assertions.assertThat(result).isNotNull()
                .isInstanceOf(MinAndMaxValueDTO.class);
    }


    @Test
    void getCurrencyMinAndMaxValueFromNIncorrectQuotations() throws InvalidAmountOfQuotationsException {
        //given
        BDDMockito.given(nbpClient.getMinAndMaxAverageValue(correctCurrency, incorrectTopCount)).willReturn(currencyAverageDTO);

        //when
        ThrowableAssert.ThrowingCallable result = () -> underTest.getMinAndMaxValueFromNQuotations(correctCurrency, incorrectTopCount);

        //then
        Assertions.assertThatException().isThrownBy(result).isNotNull();
    }
}

