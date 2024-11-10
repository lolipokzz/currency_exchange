package com.example.test.Service;

import com.example.test.DAO.CurrencyDAO;
import com.example.test.DAO.ExchangeRatesDAO;
import com.example.test.DTO.ExchangeRatesDTO;
import com.example.test.DTO.ExchangeRequestDTO;
import com.example.test.DTO.ExchangeResponseDTO;
import com.example.test.Entity.Currency;
import com.example.test.Entity.ExchangeRate;
import com.example.test.Exception.ExchangeRateNotFound;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;


public class ExchangeService {
    private static final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
    private static final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private static final CurrencyDAO currencyDAO = new CurrencyDAO();
    public ExchangeResponseDTO exchange(ExchangeRequestDTO exchangeRequest) {
        BigDecimal amount = exchangeRequest.getAmount();
        Optional<ExchangeRate> directExchangeRateOptional = exchangeRatesDAO.findExchangeRatebyCode(exchangeRequest.getBaseCurrencyCode(), exchangeRequest.getTargetCurrencyCode());
        Optional<ExchangeRate> inDirectExchangeRateOptional = exchangeRatesDAO.findExchangeRatebyCode(exchangeRequest.getTargetCurrencyCode(), exchangeRequest.getBaseCurrencyCode());
        Optional<Currency> baseCurrencyOptional = currencyDAO.findByCode(exchangeRequest.getBaseCurrencyCode());
        Optional<Currency> targetCurrencyOptional = currencyDAO.findByCode(exchangeRequest.getTargetCurrencyCode());
        if (directExchangeRateOptional.isPresent() && baseCurrencyOptional.isPresent() && targetCurrencyOptional.isPresent()) {
            ExchangeRate exchangeRate = directExchangeRateOptional.get();
            Currency baseCurrency = baseCurrencyOptional.get();
            Currency targetCurrency = targetCurrencyOptional.get();
            BigDecimal rate = exchangeRate.getRate();
            BigDecimal convertedAmount = amount.multiply(rate);
            return new ExchangeResponseDTO(baseCurrency,targetCurrency,rate,amount,convertedAmount);
        } else if (inDirectExchangeRateOptional.isPresent() && baseCurrencyOptional.isPresent() && targetCurrencyOptional.isPresent()) {
            ExchangeRate exchangeRate = inDirectExchangeRateOptional.get();
            Currency baseCurrency = baseCurrencyOptional.get();
            Currency targetCurrency = targetCurrencyOptional.get();
            BigDecimal dividend = new BigDecimal("1");
            BigDecimal rate = dividend.divide(exchangeRate.getRate(), 6, RoundingMode.HALF_UP);
            BigDecimal convertedAmount = rate.multiply(amount);
            return new ExchangeResponseDTO(baseCurrency,targetCurrency,rate,amount,convertedAmount);
        } else {
            throw new ExchangeRateNotFound("No exchange rates was found");
        }

    }

}
