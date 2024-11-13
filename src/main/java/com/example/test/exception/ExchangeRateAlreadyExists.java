package com.example.test.exception;

public class ExchangeRateAlreadyExists extends RuntimeException {
    public ExchangeRateAlreadyExists(String message) {
        super(message);
    }
}
