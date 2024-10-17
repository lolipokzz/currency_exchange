package com.example.test.Exception;

public class DbConnectionException extends RuntimeException {
    public DbConnectionException(String message) {
        super(message);
    }
}
