package com.example.test.DTO;

import com.example.test.DAO.CurrencyDAO;
import com.example.test.Entity.Currency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@Setter
@Getter

public class ExchangeRatesDTO {
    private int id;
    private Currency baseCurrency;
    private Currency targetCurrency;
    private BigDecimal rate;
    public ExchangeRatesDTO(Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }
}
