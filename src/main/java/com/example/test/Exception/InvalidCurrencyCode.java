package com.example.test.Exception;

public class InvalidCurrencyCode extends RuntimeException {
    public InvalidCurrencyCode(String message) {
        super(message);
    }
}
