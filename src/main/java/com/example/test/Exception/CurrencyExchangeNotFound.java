package com.example.test.Exception;

public class CurrencyExchangeNotFound extends RuntimeException {
    public CurrencyExchangeNotFound(String message) {
        super(message);
    }
}
