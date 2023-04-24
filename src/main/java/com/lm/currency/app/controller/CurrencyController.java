package com.lm.currency.app.controller;

import com.lm.currency.app.exceptions.InvalidAmountOfQuotationsException;
import com.lm.currency.app.exceptions.InvalidDateException;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.model.MaxDifferenceDTO;
import com.lm.currency.app.model.MinAndMaxValueDTO;
import com.lm.currency.app.service.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Currency;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{code}/{date}/")
    public ResponseEntity<CurrencyValueDTO> getCurrencyValueAtDate(@PathVariable @Valid Currency code,
                                                                   @PathVariable @Valid LocalDate date) throws InvalidDateException {
        return ResponseEntity.ok(currencyService.getCurrencyValueAtDate(code, date));
    }


    @GetMapping("/average/{code}/last/{topCount}/")
    public ResponseEntity<MinAndMaxValueDTO> getMinAndMaxValueFromNQuotations(@PathVariable @Valid Currency code,
                                                                              @PathVariable int topCount) throws InvalidAmountOfQuotationsException {
        return ResponseEntity.ok(currencyService.getMinAndMaxValueFromNQuotations(code, topCount));
    }


    @GetMapping("/BidAndAsk/{code}/last/{topCount}/")
    public ResponseEntity<MaxDifferenceDTO> getMaxDifferenceBetweenBuyAndAsk(@PathVariable @Valid Currency code,
                                                                             @PathVariable int topCount) throws InvalidAmountOfQuotationsException {
        return ResponseEntity.ok(currencyService.getMaxDifferenceBetweenBuyAndAsk(code, topCount));
    }

}



