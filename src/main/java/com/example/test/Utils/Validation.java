package com.example.test.Utils;

import com.example.test.DAO.CurrencyDAO;
import com.example.test.DAO.ExchangeRatesDAO;
import com.example.test.Entity.Currency;
import com.example.test.Entity.ExchangeRate;
import com.example.test.Exception.*;

import java.math.BigDecimal;

public class Validation {
    private static final CurrencyDAO currencyDAO = new CurrencyDAO();
    private static final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
    public static void validateCode(String code){
        if (code.isEmpty()){
            throw new CurrencyCodeIsEmpty("Currency code is empty");
        }
        if (code.length()!=3){
            throw new InvalidCurrencyCode("Invalid currency code");
        }
    }
    public static void validateNewCurrency(Currency currency){
        if (currency.getCode().length()!=3){
            throw new InvalidCurrencyCode("Invalid currency code");
        }
        if (currencyDAO.findByCode(currency.getCode()).isPresent()){
            throw new CurrencyAlreadyExists("Currency already exists");
        }
        if (currency.getCode().isEmpty() || currency.getFullName().isEmpty() || currency.getSign().isEmpty()){
            throw new InvalidCurrencyInput("Incorrect currency input");
        }
        if (currency.getCode().length()!=1){
            throw new InvalidCurrencySign("Invalid currency sign");
        }
    }
    public static void validateExchangeRate(String exchangeRate){
        if (exchangeRate.isEmpty()){
            throw new InvalidCurrencyInput("Invalid currency exchange input");
        }
        if (exchangeRate.length()!=6){
            throw new CurrencyExchangeNotFound("Invalid currency exchange");
        }
        if (currencyDAO.findByCode(exchangeRate).isEmpty()){
            throw new CurrencyExchangeNotFound("Currency exchange not found");
        }
    }
    public static void validateNewExchangeRate(ExchangeRate exchangeRate){
        if (currencyDAO.findById(exchangeRate.getBaseCurrencyId()).isEmpty() || currencyDAO.findById(exchangeRate.getTargetCurrencyId()).isEmpty() || exchangeRate.getRate().equals(BigDecimal.ZERO)){
            throw new InvalidCurrencyExchangeInput("Invalid currency exchange input");
        }
        if (exchangeRatesDAO.findExchangeRatebyID(exchangeRate.getBaseCurrencyId(), exchangeRate.getTargetCurrencyId()).isPresent()){
            throw new ExchangeRateAlreadyExists("Exchange rate already exists");
        }
        if (currencyDAO.findById(exchangeRate.getBaseCurrencyId()).isEmpty() || currencyDAO.findById(exchangeRate.getTargetCurrencyId()).isEmpty()){
            throw new CurrencyNotFoundException("Currency does not exist");
        }

    }
}
