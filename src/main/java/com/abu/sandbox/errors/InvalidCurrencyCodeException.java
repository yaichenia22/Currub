package com.abu.sandbox.errors;

public class InvalidCurrencyCodeException extends RuntimeException {

    public InvalidCurrencyCodeException() {
    }

    public InvalidCurrencyCodeException(String message) {
        super(message);
    }

    public InvalidCurrencyCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
