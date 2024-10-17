package com.example.test.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Try {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:/Users/lalka/IdeaProjects/test/src/main/resources/db.db";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Подключение к базе данных успешно!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

