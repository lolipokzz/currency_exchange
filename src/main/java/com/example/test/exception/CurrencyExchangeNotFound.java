package com.example.test.exception;

public class CurrencyExchangeNotFound extends RuntimeException {
    public CurrencyExchangeNotFound(String message) {
        super(message);
    }
}
