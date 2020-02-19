package com.abu.sandbox.controller;

import com.abu.sandbox.currencysource.api.CurrencyRate;
import com.abu.sandbox.currencysource.api.CurrencyRateSource;
import com.abu.sandbox.util.CurrencyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Currency;

@RestController
@RequestMapping("/currency")
class CurrencyRateController {

    @Autowired
    private CurrencyRateSource currencyRateSource;

    @GetMapping
    private Flux<CurrencyRate> getAllRates() {
        return currencyRateSource.getCurrencies();
    }

    @GetMapping("/{code}")
    private Mono<CurrencyRate> getCurrencyRateByCode(@PathVariable String code) {
        Currency currency = CurrencyProvider.getCurrency(code.toUpperCase());
        return currencyRateSource.getCurrency(currency);
    }
}
