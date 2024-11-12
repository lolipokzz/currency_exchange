package com.example.test.Exception;

public class ExchangeRateAlreadyExists extends RuntimeException {
    public ExchangeRateAlreadyExists(String message) {
        super(message);
    }
}
