package com.example.test.exception;

public class InvalidCurrencySign extends RuntimeException {
    public InvalidCurrencySign(String message) {
        super(message);
    }
}
