package com.example.test.Exception;

public class InvalidCurrencyExchangeInput extends RuntimeException {
    public InvalidCurrencyExchangeInput(String message) {
        super(message);
    }
}
