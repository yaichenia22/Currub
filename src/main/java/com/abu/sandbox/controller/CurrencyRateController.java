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
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyRateController {

    @Autowired
    private CurrencyRateSource currencyRateSource;

    @GetMapping
    private Flux<CurrencyRate> getAllRates() {
        List<CurrencyRate> rates = currencyRateSource.getCurrencies();
        return Flux.fromIterable(rates);
    }

    @GetMapping("/{code}")
    private Mono<CurrencyRate> getCurrencyRateByCode(@PathVariable String code) {
        Currency currency = CurrencyProvider.getCurrency(code.toUpperCase());
        CurrencyRate currencyRate = currencyRateSource.getCurrency(currency);
        return Mono.just(currencyRate);
    }
}
