package com.lm.currency.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lm.currency.app.model.CurrencyAverageDTO;
import com.lm.currency.app.model.CurrencyAverageRatesDTO;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.model.MinAndMaxValueDTO;
import com.lm.currency.app.webclient.NBPClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CurrencyServiceTest {


    NBPClient nbpClient = mock(NBPClient.class);
    CurrencyService currencyService = new CurrencyService(nbpClient);
    Currency currencyCode = Currency.getInstance("USD");

//    @Test
//    public void testGetCurrencyValueAtDate() throws JsonProcessingException {
//        NBPClient mockNBPClient = Mockito.mock(NBPClient.class);
//        CurrencyService service = new CurrencyService(mockNBPClient);
//
//        Currency currencyCode = Currency.getInstance("USD");
//        LocalDate date = LocalDate.parse("2023-04-22");
//
//        CurrencyAverageRatesDTO rate1 = new CurrencyAverageRatesDTO(1.0);
//        List<CurrencyAverageRatesDTO> rates = List.of(rate1);
//        CurrencyAverageDTO responseDTO = new CurrencyAverageDTO(rates);
//        Mockito.when(mockNBPClient.getCurrencyValue(currencyCode, date)).thenReturn(responseDTO);
//
//        CurrencyValueDTO result = service.getCurrencyValueAtDate(currencyCode, date);
//
//        assertNotNull(result);
//        assertNotNull(result.getAverageExchangeRate());
//        assertEquals(1.0, result.getAverageExchangeRate(), 0.0);
//    }










//    @Test
//    public void testGetMinAndMaxValueFromNQuotations() {
//        CurrencyService obj = new CurrencyService(nbpClient);
//        Currency currencyCode = Currency.getInstance("USD");
//        int topCount = 5;
//        MinAndMaxValueDTO result = obj.getMinAndMaxValueFromNQuotations(currencyCode, topCount);
//        assertNotNull(result);
//        assertNotNull(result.getMinExchangeRateValue());
//        assertNotNull(result.getMaxExchangeRateValue());
//        assertTrue(result.getMinExchangeRateValue() <= result.getMaxExchangeRateValue());
//    }





//
//    @Test
//    void testGetMinAndMaxValueFromNQuotations() {
//        // Arrange
//        CurrencyAverageDTO currencyAverageDTO = new CurrencyAverageDTO();
//        List<CurrencyAverageRatesDTO> rates = Arrays.asList(new CurrencyAverageRatesDTO(1.0),
//                new CurrencyAverageRatesDTO(2.0),
//                new CurrencyAverageRatesDTO(3.0));
//        currencyAverageDTO.setRates(rates);
//        when(nbpClient.getMinAndMaxAverageValue(any(), anyInt())).thenReturn(currencyAverageDTO);
//
//        // Act
//        MinAndMaxValueDTO result = currencyService.getMinAndMaxValueFromNQuotations(USD, 3);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1.0, result.getMinExchangeRateValue());
//        assertEquals(3.0, result.getMaxExchangeRateValue());
//    }

    @Test
    void testGetMinAndMaxValueFromNQuotationsWithEmptyRates() {
        // Arrange
        CurrencyAverageDTO currencyAverageDTO = new CurrencyAverageDTO();
        currencyAverageDTO.setRates(new ArrayList<>());
        when(nbpClient.getMinAndMaxAverageValue(any(Currency.class), anyInt())).thenReturn(currencyAverageDTO);

        // Act
        MinAndMaxValueDTO result = currencyService.getMinAndMaxValueFromNQuotations(currencyCode, 3);

        // Assert
        assertNotNull(result);
        assertNull(result.getMinExchangeRateValue());
        assertNull(result.getMaxExchangeRateValue());
    }

}