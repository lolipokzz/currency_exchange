package com.example.test.exception;

public class DbConnectionException extends RuntimeException {
    public DbConnectionException(String message) {
        super(message);
    }
}
