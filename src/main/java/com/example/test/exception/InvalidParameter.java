package com.example.test.exception;

public class InvalidParameter extends RuntimeException {
    public InvalidParameter(String message) {
        super(message);
    }
}
