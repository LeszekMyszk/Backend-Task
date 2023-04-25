//package com.lm.currency.app.service;
//
//import com.lm.currency.app.exceptions.InvalidDateException;
//import jakarta.validation.Validator;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@AllArgsConstructor
//@Builder
//public class CurrencyRatesValidator {
//
//    private final LocalDate date;
//    private final currencyCode code;
//    private final int amountOfQuotations;
//    List<String> errorMessage;
//
//    boolean checkDate (date){
//        (date.plusDays(1).isAfter(LocalDate.now()) || date.plusYears(2).isBefore(LocalDate.now());)
//    }
//
//        }
//        throw new InvalidDateException("You can access exchange rates in period from two years ago until yesterday.");
//    }
//}