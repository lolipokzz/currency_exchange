package com.example.test.service;

import com.example.test.dao.CurrencyDAO;
import com.example.test.dao.ExchangeRatesDAO;
import com.example.test.dto.ExchangeRatesDTO;
import com.example.test.entity.Currency;
import com.example.test.entity.ExchangeRate;

public class ExchangeRateService {
    private final CurrencyDAO currencyDAO = new CurrencyDAO();
    private final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
    public ExchangeRatesDTO getExchangeRatesDTO(ExchangeRate exchangeRate) {
        int baseCurrencyID = exchangeRate.getBaseCurrencyId();
        int targetCurrencyID = exchangeRate.getTargetCurrencyId();
        Currency baseCurrency = currencyDAO.findById(baseCurrencyID).orElseThrow(()-> new RuntimeException("not found"));
        Currency targetCurrency = currencyDAO.findById(targetCurrencyID).orElseThrow(()-> new RuntimeException("not found"));
        ExchangeRatesDTO exchangeRatesDTO = new ExchangeRatesDTO(baseCurrency,targetCurrency, exchangeRate.getRate());
        return exchangeRatesDTO;
    }
}
