package com.example.test.DAO;

import com.example.test.Exception.DbConnectionException;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Paths;

public class Connector {
    public static Connection getConnection() throws DbConnectionException {
        String url = "jdbc:sqlite:C:/Users/lalka/IdeaProjects/test/src/main/resources/db.db";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(url)) {
            if(conn == null) {
                throw new DbConnectionException("CONNECTION IS NULL");
            }
            return conn;
            } catch (SQLException e) {
                throw new DbConnectionException("db"+e.getMessage());
            }

    }

}

