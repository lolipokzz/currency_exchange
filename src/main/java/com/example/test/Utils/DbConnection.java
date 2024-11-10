package com.example.test.Utils;

import com.example.test.Exception.DbConnectionException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;



public class DbConnection {
    private static final HikariConfig hikariConfig= new HikariConfig("/application.properties");
    private static final DataSource dataSource = new HikariDataSource(hikariConfig);
    public static Connection getConnection() throws DbConnectionException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

