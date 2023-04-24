package com.lm.currency.app.controller;

import com.lm.currency.app.service.CurrencyService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CurrencyService currencyService;
    CurrencyController currencyController = new CurrencyController(currencyService);

    @Test
    void getCurrencyValueAtDate() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/USD/2023-04-20/");
        //RequestBuilder builder = MockMvcRequestBuilders.get("/USD/2023-04-20/");
        mockMvc.perform(builder)
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0)));

        //String resBody = mockMvc.perform(builder).andReturn().getResponse().getContentAsString();
      //  mockMvc.perform(get("/USD/2023-04-20/")).andExpect(status().isOk());

   }
}