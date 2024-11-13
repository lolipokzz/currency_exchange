package com.example.test.exception;

public class CurrencyCodeIsEmpty extends RuntimeException {
    public CurrencyCodeIsEmpty(String message) {
        super(message);
    }
}
