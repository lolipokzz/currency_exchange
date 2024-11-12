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

import static java.math.MathContext.DECIMAL64;


public class ExchangeService {
    private static final ExchangeRatesDAO exchangeRatesDAO = new ExchangeRatesDAO();
    private static final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private static final CurrencyDAO currencyDAO = new CurrencyDAO();
    public ExchangeResponseDTO exchange(ExchangeRequestDTO exchangeRequest) {;
        Optional<ExchangeRate> directExchangeRateOptional = exchangeRatesDAO.findExchangeRatebyCode(exchangeRequest.getBaseCurrencyCode(), exchangeRequest.getTargetCurrencyCode());
        Optional<ExchangeRate> inDirectExchangeRateOptional = exchangeRatesDAO.findExchangeRatebyCode(exchangeRequest.getTargetCurrencyCode(), exchangeRequest.getBaseCurrencyCode());
        Optional<ExchangeRate> usdToBaseCurrencyRateOptional = exchangeRatesDAO.findExchangeRatebyCode("USD", exchangeRequest.getBaseCurrencyCode());
        Optional<ExchangeRate> usdToTargetCurrencyRateOptional = exchangeRatesDAO.findExchangeRatebyCode("USD",exchangeRequest.getTargetCurrencyCode());
        Optional<Currency> baseCurrencyOptional = currencyDAO.findByCode(exchangeRequest.getBaseCurrencyCode());
        Optional<Currency> targetCurrencyOptional = currencyDAO.findByCode(exchangeRequest.getTargetCurrencyCode());
        BigDecimal amount = exchangeRequest.getAmount();
        if (directExchangeRateOptional.isPresent() && baseCurrencyOptional.isPresent() && targetCurrencyOptional.isPresent()) {
            ExchangeRate exchangeRate = directExchangeRateOptional.get();
            Currency baseCurrency = baseCurrencyOptional.get();
            Currency targetCurrency = targetCurrencyOptional.get();
            BigDecimal rate = exchangeRate.getRate();
            BigDecimal convertedAmount = amount.multiply(rate).setScale(6, RoundingMode.HALF_EVEN);
            return new ExchangeResponseDTO(baseCurrency,targetCurrency,rate,amount,convertedAmount);
        } else if (inDirectExchangeRateOptional.isPresent() && baseCurrencyOptional.isPresent() && targetCurrencyOptional.isPresent()) {
            ExchangeRate indirectExchangeRate = inDirectExchangeRateOptional.get();
            BigDecimal rate = BigDecimal.ONE.divide(indirectExchangeRate.getRate(), DECIMAL64).setScale(6, RoundingMode.HALF_EVEN);
            ExchangeRatesDTO exchangeRatesDTO = exchangeRateService.getExchangeRatesDTO(indirectExchangeRate);
            BigDecimal convertedAmount = amount.multiply(rate);
            return new ExchangeResponseDTO(exchangeRatesDTO.getBaseCurrency(),exchangeRatesDTO.getTargetCurrency(),exchangeRatesDTO.getRate(),amount,convertedAmount);
        } else if (usdToBaseCurrencyRateOptional.isPresent() && usdToTargetCurrencyRateOptional.isPresent() && baseCurrencyOptional.isPresent() && targetCurrencyOptional.isPresent()) {
            ExchangeRate USDbaseCurrencyRate = usdToBaseCurrencyRateOptional.get();
            ExchangeRate USDTargetCurrencyRate = usdToTargetCurrencyRateOptional.get();
            BigDecimal rate = USDTargetCurrencyRate.getRate().divide(USDbaseCurrencyRate.getRate(), DECIMAL64).setScale(6, RoundingMode.HALF_EVEN);
            BigDecimal convertedAmount = amount.multiply(rate);
            Currency baseCurrency = baseCurrencyOptional.get();
            Currency targetCurrency = targetCurrencyOptional.get();
            return new ExchangeResponseDTO(baseCurrency,targetCurrency,rate,amount,convertedAmount);
        } else {
            throw new ExchangeRateNotFound("No exchange rates was found");
        }
    }

}
