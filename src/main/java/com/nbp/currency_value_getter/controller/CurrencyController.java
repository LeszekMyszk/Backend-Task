package com.nbp.currency_value_getter.controller;

import com.nbp.currency_value_getter.model.CurrencyDto;
import com.nbp.currency_value_getter.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    CurrencyDto getCurrencyValueAtDate(){
        return currencyService.getCurrencyValueAtDate();
    }
}

//  CurrencyDto getCurrencyValueAtDate(@PathVariable("code") String code, @PathVariable("date") LocalDate date){
//   @GetMapping("/exchanges/{code}/{date}")