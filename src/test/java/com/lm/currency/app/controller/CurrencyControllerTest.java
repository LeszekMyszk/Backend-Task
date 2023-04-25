package com.lm.currency.app.controller;


import com.lm.currency.app.configuration.TimeService;
import com.lm.currency.app.service.CurrencyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CurrencyService currencyService;
    @MockBean
    TimeService timeService;

    @Test
    void mockMvcNotEmpty() {
        //given
        //when
        //then
        Assertions.assertThat(mockMvc).isNotNull();
    }


    @Test
    void givenCorrectCurrency() throws Exception {
        //given
        //when-then
        mockMvc.perform(get("/api/v1/currency/USD/2022-04-28/"))
                .andExpect(status().isOk());
    }

    @Test
    void givenWrongCurrency() throws Exception {
        //given
        //when-then
        mockMvc.perform(get("/api/v1/currency/USA/2022-04-28/"))
                .andExpect(status().isBadRequest());
    }
}

