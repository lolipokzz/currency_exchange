package com.example.test.Exception;

public class ExchangeRateNotFound extends RuntimeException {
    public ExchangeRateNotFound(String message) {
        super(message);
    }
}
