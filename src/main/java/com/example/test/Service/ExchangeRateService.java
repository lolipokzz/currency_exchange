package com.example.test.Service;

import com.example.test.DAO.CurrencyDAO;
import com.example.test.DAO.ExchangeRatesDAO;
import com.example.test.DTO.ExchangeRatesDTO;
import com.example.test.Entity.Currency;
import com.example.test.Entity.ExchangeRate;

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
