package com.abu.sandbox.util;

import com.abu.sandbox.errors.InvalidCurrencyCodeException;

import java.util.Currency;

public class CurrencyProvider {

    public static Currency getCurrency(String code) {
        try {
            return Currency.getInstance(code);
        } catch (IllegalArgumentException ex) {
            throw new InvalidCurrencyCodeException(ex.getMessage(), ex);
        }
    }
}
