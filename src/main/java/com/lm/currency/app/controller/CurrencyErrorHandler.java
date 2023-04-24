package com.lm.currency.app.controller;

import com.lm.currency.app.configuration.ErrorMessage;
import com.lm.currency.app.configuration.TimeService;
import com.lm.currency.app.exceptions.InvalidAmountOfQuotationsException;
import com.lm.currency.app.exceptions.InvalidDateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CurrencyErrorHandler {
    private final TimeService timeService;

    CurrencyErrorHandler(TimeService timeService) {
        this.timeService = timeService;
    }

    @ExceptionHandler(InvalidAmountOfQuotationsException.class)
    ResponseEntity<ErrorMessage> handleInvalidAmountOfQuotations(InvalidAmountOfQuotationsException exception) {
        ErrorMessage errorMessage = new ErrorMessage(timeService.get(), exception.getErrorInfo());
        return ResponseEntity.badRequest().body(errorMessage);
    }


    @ExceptionHandler(InvalidDateException.class)
    ResponseEntity<ErrorMessage> handleInvalidDate(InvalidDateException exception) {
        ErrorMessage errorMessage = new ErrorMessage(timeService.get(), exception.getErrorInfo());
        return ResponseEntity.badRequest().body(errorMessage);
    }


}
