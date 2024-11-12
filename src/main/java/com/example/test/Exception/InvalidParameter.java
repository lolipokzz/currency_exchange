package com.example.test.Exception;

public class InvalidParameter extends RuntimeException {
    public InvalidParameter(String message) {
        super(message);
    }
}
