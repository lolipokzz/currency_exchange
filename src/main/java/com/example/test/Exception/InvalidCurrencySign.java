package com.example.test.Exception;

public class InvalidCurrencySign extends RuntimeException {
    public InvalidCurrencySign(String message) {
        super(message);
    }
}
