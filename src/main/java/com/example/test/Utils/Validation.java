package com.example.test.Utils;

import com.example.test.DAO.CurrencyDAO;
import com.example.test.Entity.Currency;
import com.example.test.Exception.CurrencyAlreadyExists;
import com.example.test.Exception.CurrencyExchangeNotFound;
import com.example.test.Exception.InvalidCurrencyCode;
import com.example.test.Exception.InvalidCurrencyInput;

public class Validation {
    private static final CurrencyDAO currencyDAO = new CurrencyDAO();
    public static void validateCode(String code){
        if (code.isEmpty()){
            throw new InvalidCurrencyCode("Invalid currency code");
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
    }
    public static void validateExchangeRate(String exchangeRate){
        if (exchangeRate.isEmpty()){
            throw new InvalidCurrencyInput("Invalid currency exchange input");
        }
        if (exchangeRate.length()!=6){
            throw new CurrencyExchangeNotFound("Invalid currency exchange");
        }
    }
}
