package com.example.test.Exception;

public class CurrencyAlreadyExists extends RuntimeException {
    public CurrencyAlreadyExists(String message) {
        super(message);
    }
}
