package com.example.test.DAO;
import com.example.test.Entity.Currency;
import com.example.test.Entity.ExchangeRate;
import com.example.test.Exception.CurrencyNotFoundException;
import com.example.test.Exception.ExchangeRateInputFail;
import com.example.test.Exception.ExchangeRateNotFound;
import com.example.test.Utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExchangeRatesDAO {
    public List<ExchangeRate> getAllExchangeRates(){
        String query = "SELECT\n" +
                "    ExchangeRates.id,\n" +
                "    baseCurrency.id AS BaseCurrencyId,\n" +
                "    baseCurrency.code AS BaseCurrencyCode,\n" +
                "    baseCurrency.fullName AS BaseCurrencyFullName,\n" +
                "    baseCurrency.sign AS BaseCurrencySign,\n" +
                "    targetCurrency.id AS TargetCurrencyId,\n" +
                "    targetCurrency.code AS TargetCurrencyCode,\n" +
                "    targetCurrency.fullName AS TargetCurrencyFullName,\n" +
                "    targetCurrency.sign AS TargetCurrencySign,\n" +
                "    ExchangeRates.Rate\n" +
                "FROM\n" +
                "    ExchangeRates\n" +
                "        JOIN\n" +
                "    currencies AS baseCurrency ON ExchangeRates.BaseCurrencyId = baseCurrency.id\n" +
                "        JOIN\n" +
                "    currencies AS targetCurrency ON ExchangeRates.TargetCurrencyId = targetCurrency.id;";
        try(Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<ExchangeRate> rates = new ArrayList<>();
            while (resultSet.next()){
                rates.add(ExchangeRate.getExchangeRate(resultSet));
            }
            return rates;

        }catch (SQLException e){
            throw new ExchangeRateNotFound("Exchange rate not found");
        }
    }
    public Optional<ExchangeRate>  findExchangeRatebyID(int baseCurrencyId, int targetCurrencyId) {
        String query= "SELECT\n" +
                "    ExchangeRates.id,\n" +
                "    baseCurrency.id AS BaseCurrencyId,\n" +
                "    baseCurrency.code AS BaseCurrencyCode,\n" +
                "    baseCurrency.fullName AS BaseCurrencyFullName,\n" +
                "    baseCurrency.sign AS BaseCurrencySign,\n" +
                "    targetCurrency.id AS TargetCurrencyId,\n" +
                "    targetCurrency.code AS TargetCurrencyCode,\n" +
                "    targetCurrency.fullName AS TargetCurrencyFullName,\n" +
                "    targetCurrency.sign AS TargetCurrencySign,\n" +
                "    ExchangeRates.Rate\n" +
                "FROM\n" +
                "    ExchangeRates\n" +
                "        JOIN\n" +
                "    currencies AS baseCurrency ON ExchangeRates.BaseCurrencyId = baseCurrency.id\n" +
                "        JOIN\n" +
                "    currencies AS targetCurrency ON ExchangeRates.TargetCurrencyId = targetCurrency.id\n" +
                "where BaseCurrencyId = ? and  TargetCurrencyId =? ";
        try(Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, baseCurrencyId);
            statement.setInt(2, targetCurrencyId);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(ExchangeRate.getExchangeRate(resultSet));
        }catch (SQLException e){
            throw new ExchangeRateNotFound("Exchange rate not found");
        }
    }
    public void addNewExchangeRate(ExchangeRate exchangeRate) {
        String query = "insert into ExchangeRates(basecurrencyid, targetcurrencyid, rate) VALUES (?,?,?)";
        try(Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, exchangeRate.getBaseCurrencyId());
            statement.setInt(2, exchangeRate.getTargetCurrencyId());
            statement.setBigDecimal(3, exchangeRate.getRate());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new ExchangeRateInputFail("Exchange rate input failed");
        }
    }
    public void setRate(ExchangeRate exchangeRate) {
        String query = "UPDATE ExchangeRates set Rate = ? where BaseCurrencyId =? and TargetCurrencyId =?";
        try(Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)){
            statement.setBigDecimal(1, exchangeRate.getRate());
            statement.setInt(2, exchangeRate.getBaseCurrencyId());
            statement.setInt(3, exchangeRate.getTargetCurrencyId());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new ExchangeRateNotFound("Exchange rate not found");
        }
    }
    public Optional<ExchangeRate>  findExchangeRatebyCode(String baseCurrencyCode, String targetCurrencyCode) {
        String query = "SELECT\n" +
                "    ExchangeRates.id,\n" +
                "    baseCurrency.id AS BaseCurrencyId,\n" +
                "    baseCurrency.code AS BaseCurrencyCode,\n" +
                "    baseCurrency.fullName AS BaseCurrencyFullName,\n" +
                "    baseCurrency.sign AS BaseCurrencySign,\n" +
                "    targetCurrency.id AS TargetCurrencyId,\n" +
                "    targetCurrency.code AS TargetCurrencyCode,\n" +
                "    targetCurrency.fullName AS TargetCurrencyFullName,\n" +
                "    targetCurrency.sign AS TargetCurrencySign,\n" +
                "    ExchangeRates.Rate\n" +
                "FROM\n" +
                "    ExchangeRates\n" +
                "        JOIN\n" +
                "    currencies AS baseCurrency ON ExchangeRates.BaseCurrencyId = baseCurrency.id\n" +
                "        JOIN\n" +
                "    currencies AS targetCurrency ON ExchangeRates.TargetCurrencyId = targetCurrency.id\n" +
                "where BaseCurrencyCode = ? and  TargetCurrencyCode =? ";
        try (Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, baseCurrencyCode);
            statement.setString(2, targetCurrencyCode);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
            return Optional.of(ExchangeRate.getExchangeRate(resultSet));
            }
        } catch (SQLException e) {
            throw new ExchangeRateNotFound("Exchange rate not found!");
        }
        return Optional.empty();
    }
}
