package com.example.test.Service;

import com.example.test.DTO.CurrencyDTO;
import com.example.test.Entity.Currency;

public class CurrencyService {
    public CurrencyDTO convertToCurrencyDTO(Currency currency) {
        return new CurrencyDTO(currency.getId(),currency.getCode(), currency.getFullName(), currency.getSign());
    }
}
