package com.example.test.Entity;


import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Currency {
    private int id;
    private String code;
    private String fullName;
    private String sign;
    public Currency( String code, String fullName, String sign ){
        this.code = code;
        this.fullName = fullName;
        this.sign=sign;
    }
    public static Currency getCurrency(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String code = resultSet.getString("code");
        String fullName = resultSet.getString("fullName");
        String sign = resultSet.getString("sign");
        return new Currency(id,code, fullName, sign);
    }
    public static Currency addCurrency(ResultSet resultSet) throws SQLException{
        String code = resultSet.getString("code");
        String fullName = resultSet.getString("fullName");
        String sign = resultSet.getString("sign");
        return new Currency(code, fullName, sign);
    }


}
