package com.example.test.exception;

public class ExchangeRateNotFound extends RuntimeException {
    public ExchangeRateNotFound(String message) {
        super(message);
    }
}
