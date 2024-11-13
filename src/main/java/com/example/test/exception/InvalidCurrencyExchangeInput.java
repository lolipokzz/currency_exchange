package com.example.test.exception;

public class InvalidCurrencyExchangeInput extends RuntimeException {
    public InvalidCurrencyExchangeInput(String message) {
        super(message);
    }
}
