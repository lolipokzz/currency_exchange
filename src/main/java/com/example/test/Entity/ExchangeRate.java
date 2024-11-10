package com.example.test.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeRate {
    private int id;
    private int baseCurrencyId;
    private int targetCurrencyId;
    private BigDecimal rate;
    public static ExchangeRate getExchangeRate(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int baseCurrencyId = resultSet.getInt("baseCurrencyId");
        int targetCurrencyId = resultSet.getInt("targetCurrencyId");
        BigDecimal rate = resultSet.getBigDecimal("rate");
        return new  ExchangeRate(id, baseCurrencyId, targetCurrencyId, rate);
    }
    public ExchangeRate(int baseCurrencyId, int targetCurrencyId, BigDecimal rate) {
        this.baseCurrencyId = baseCurrencyId;
        this.targetCurrencyId = targetCurrencyId;
        this.rate = rate;
    }
}
