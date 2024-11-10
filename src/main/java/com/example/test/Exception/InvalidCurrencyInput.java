package com.example.test.Exception;

public class InvalidCurrencyInput extends RuntimeException {
    public InvalidCurrencyInput(String message) {
        super(message);
    }
}
