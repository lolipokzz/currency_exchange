package com.example.test.DAO;

import com.example.test.Entity.Currency;
import com.example.test.Exception.CurrencyNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyInfo {
    public List<Currency> getAllCurrencies(){
    String query = "SELECT * from currencies";
    try(Connection connection = Connector.getConnection(); Statement statement = connection.createStatement()) {
        ResultSet resultSet = statement.executeQuery(query);
        List<Currency> currencies = new ArrayList<>();
        while (resultSet.next()){
            currencies.add(Currency.getCurrency(resultSet));
        }
        return currencies;
    }catch (SQLException e){
        throw new CurrencyNotFoundException("Currency not found");
    }

}
    public Optional<Currency> findById(int ID){
        String query = "SELECT id,Code,Fullname,Sign from currencies where id = ?";
        try(Connection connection = Connector.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return Optional.of(Currency.getCurrency(resultSet));
            }
        }catch (SQLException e){
            throw new CurrencyNotFoundException("Currency not found");
        }
        return Optional.empty();
    }
    public void addNewCurrency(String code, String name, char sign){
        String query = "insert into currencies(code, fullname, sign)  values (code,name,sign)";
        try(Connection connection = Connector.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(2,code);
            statement.setString(3,name);
            statement.setString(3, String.valueOf(sign));
            ResultSet resultSet = statement.executeQuery(query);
        }catch (SQLException e){
            throw new CurrencyNotFoundException("Currency not found");
        }
    }
    public Optional<Currency> findByCode(String code){
        String query = "SELECT id,Code,Fullname,Sign from currencies where code = ?";
        try(Connection connection = Connector.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return Optional.of(Currency.getCurrency(resultSet));
            }
        }catch (SQLException e){
            throw new CurrencyNotFoundException("Currency not found");
        }
        return Optional.empty();
    }
}
