package com.example.test.exception;

public class InvalidCurrencyCode extends RuntimeException {
    public InvalidCurrencyCode(String message) {
        super(message);
    }
}
