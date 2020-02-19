package com.abu.sandbox.currencysource.api;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Currency;

public interface CurrencyRateSource {

    Mono<CurrencyRate> getCurrency(Currency currency);

    Flux<CurrencyRate> getCurrencies();
}
