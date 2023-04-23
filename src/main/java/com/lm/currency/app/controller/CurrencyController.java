package com.lm.currency.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lm.currency.app.model.CurrencyValueDTO;
import com.lm.currency.app.model.MinAndMaxValueDTO;
import com.lm.currency.app.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> getMinAndMaxValueFromNQuotations(@PathVariable String code,
                                                                   @PathVariable int topCount) throws JsonProcessingException {
        return ResponseEntity.ok(currencyService.getMinAndMaxValueFromNQuotations(code, topCount));
    }


    @GetMapping("/C/{code}/last/{topCount}/")
    public ResponseEntity<String> getMaxDifferenceBetweenBuyAndAsk(@PathVariable String code,
                                                                   @PathVariable int topCount) throws JsonProcessingException {
        return ResponseEntity.ok(currencyService.getMaxDifferenceBetweenBuyAndAsk(code, topCount));
    }


}

//      3) Given a currency code and the number of last quotations N (N <= 255), provide the major difference between the buy and ask rate (every day has different rates).
//                URL: "http://api.nbp.pl/api/exchangerates/rates/c/usd/last/10/?format=json"
//                validation: N <= 255
//                check differences between bid and ask
//                return: max difference

//      2) Given a currency code and the number of last quotations N (N <= 255), provide the max and min average value (every day has a different average).
//                URL: "http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/last/{topCount}/"
//                validation: N <= 255
//                return: min and max value

//  CurrencyDto getCurrencyValueAtDate(@PathVariable("code") String code, @PathVariable("date") LocalDate date){
//   @GetMapping("/exchanges/{code}/{date}")
