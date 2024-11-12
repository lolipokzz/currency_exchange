package com.example.test.Exception;

public class CurrencyCodeIsEmpty extends RuntimeException {
    public CurrencyCodeIsEmpty(String message) {
        super(message);
    }
}
