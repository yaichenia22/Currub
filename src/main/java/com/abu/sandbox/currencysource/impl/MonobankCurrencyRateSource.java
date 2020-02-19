package com.abu.sandbox.currencysource.impl;

import com.abu.sandbox.currencysource.api.CurrencyRate;
import com.abu.sandbox.currencysource.api.CurrencyRateSource;
import com.abu.sandbox.monobank.api.MonobankCurrencyRate;
import com.abu.sandbox.monobank.api.MonobankCurrencyRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Currency;

@Component
public class MonobankCurrencyRateSource implements CurrencyRateSource {

    @Autowired
    private CurrenciesCache currenciesCache;

    @Autowired
    private MonobankCurrencyRestClient monobankClient;

    @Override
    public Mono<CurrencyRate> getCurrency(Currency currency) {
        Flux<MonobankCurrencyRate> currencies = monobankClient.getMonoCurrenciesRates();
        return Mono.from(preprocessFlux(currencies)
                .filter(rate -> rate.getCurrencyCodeA() == currency.getNumericCode())
                .map(rate -> new CurrencyRate(currency, rate.getRateBuy(), rate.getRateSell(), rate.getDate())));
    }

    @Override
    public Flux<CurrencyRate> getCurrencies() {
        Flux<MonobankCurrencyRate> currencies = monobankClient.getMonoCurrenciesRates();
        return preprocessFlux(currencies)
                .map(rate -> new CurrencyRate(
                        currenciesCache.getCurrency(
                                rate.getCurrencyCodeA()),
                        rate.getRateBuy(),
                        rate.getRateSell(),
                        rate.getDate()));
    }

    private Flux<MonobankCurrencyRate> preprocessFlux(Flux<MonobankCurrencyRate> flux) {
        return flux
                .filter(rate -> rate.getCurrencyCodeB() == Currency.getInstance("UAH").getNumericCode())
                .filter(rate -> rate.getRateSell() != null && rate.getRateBuy() != null);
    }
}
