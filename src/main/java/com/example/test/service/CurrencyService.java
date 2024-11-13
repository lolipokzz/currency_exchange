package com.example.test.service;

import com.example.test.dto.CurrencyDTO;
import com.example.test.entity.Currency;

public class CurrencyService {
    public CurrencyDTO convertToCurrencyDTO(Currency currency) {
        return new CurrencyDTO(currency.getId(),currency.getCode(), currency.getFullName(), currency.getSign());
    }
}
