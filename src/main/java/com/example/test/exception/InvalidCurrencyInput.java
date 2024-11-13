package com.example.test.exception;

public class InvalidCurrencyInput extends RuntimeException {
    public InvalidCurrencyInput(String message) {
        super(message);
    }
}
