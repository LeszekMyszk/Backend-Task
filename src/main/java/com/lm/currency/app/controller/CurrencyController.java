package com.lm.currency.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.model.MaxDifferenceDTO;
import com.lm.currency.app.model.MinAndMaxValueDTO;
import com.lm.currency.app.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{code}/{date}/")
    public ResponseEntity<CurrencyValueDTO> getCurrencyValueAtDate(@PathVariable String code,
                                                                   @PathVariable LocalDate date) throws JsonProcessingException {
        return ResponseEntity.ok(currencyService.getCurrencyValueAtDate(code, date));
    }


    @GetMapping("/{code}/last/{topCount}/")
    public ResponseEntity<MinAndMaxValueDTO> getMinAndMaxValueFromNQuotations(@PathVariable String code,
                                                                              @PathVariable int topCount) throws JsonProcessingException {
        return ResponseEntity.ok(currencyService.getMinAndMaxValueFromNQuotations(code, topCount));
    }


    @GetMapping("/C/{code}/last/{topCount}/")
    public ResponseEntity<MaxDifferenceDTO> getMaxDifferenceBetweenBuyAndAsk(@PathVariable String code,
                                                                             @PathVariable int topCount) throws JsonProcessingException {
        return ResponseEntity.ok(currencyService.getMaxDifferenceBetweenBuyAndAsk(code, topCount));
    }

}



