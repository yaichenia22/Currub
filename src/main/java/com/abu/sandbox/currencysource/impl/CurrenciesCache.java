package com.abu.sandbox.currencysource.impl;

import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.concurrent.ConcurrentHashMap;

@Component
class CurrenciesCache {

    private ConcurrentHashMap<Integer, Currency> cache = new ConcurrentHashMap<>();

    Currency getCurrency(int numericCode) {
        return cache.computeIfAbsent(numericCode, this::findByCode);
    }

    private Currency findByCode(Integer numericCode) {
        return Currency.getAvailableCurrencies()
                .stream()
                .filter(currency -> currency.getNumericCode() == numericCode)
                .findAny()
                .orElse(null);
    }
}
