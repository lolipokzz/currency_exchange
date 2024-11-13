package com.example.test.exception;

public class CurrencyAlreadyExists extends RuntimeException {
    public CurrencyAlreadyExists(String message) {
        super(message);
    }
}
