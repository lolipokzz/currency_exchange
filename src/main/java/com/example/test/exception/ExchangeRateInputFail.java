package com.example.test.exception;

public class ExchangeRateInputFail extends RuntimeException {
    public ExchangeRateInputFail(String message) {
        super(message);
    }
}
