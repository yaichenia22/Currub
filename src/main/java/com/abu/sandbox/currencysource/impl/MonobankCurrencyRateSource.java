package com.abu.sandbox.currencysource.impl;

import com.abu.sandbox.currencysource.api.CurrencyRateSource;
import com.abu.sandbox.currencysource.api.CurrencyRate;
import com.abu.sandbox.monobank.api.MonobankCurrencyRate;
import com.abu.sandbox.monobank.api.MonobankCurrencyRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonobankCurrencyRateSource implements CurrencyRateSource {

    @Autowired
    private CurrenciesCache currenciesCache;

    @Autowired
    private MonobankCurrencyRestClient monobankClient;

    @Override
    public CurrencyRate getCurrency(Currency currency) {
        Flux<MonobankCurrencyRate> currencies = retrieveCurrencies();
        return preprocessFlux(currencies.toStream())
                .filter(rate -> rate.getCurrencyCodeA() == currency.getNumericCode())
                .findFirst()
                .map(rate -> new CurrencyRate(currency, rate.getRateBuy(), rate.getRateSell(), rate.getDate()))
                .orElse(null);
    }

    @Override
    public List<CurrencyRate> getCurrencies() {
        Flux<MonobankCurrencyRate> currencies = retrieveCurrencies();
        return preprocessFlux(currencies.toStream())
                .map(rate -> new CurrencyRate(
                        currenciesCache.getCurrency(
                                rate.getCurrencyCodeA()),
                        rate.getRateBuy(),
                        rate.getRateSell(),
                        rate.getDate()))
                .collect(Collectors.toList());
    }

    private Flux<MonobankCurrencyRate> retrieveCurrencies() {
        ClientResponse monoCurrenciesResponse = monobankClient.getMonoCurrenciesResponse();
        return monoCurrenciesResponse.bodyToFlux(MonobankCurrencyRate.class);
    }

    private Stream<MonobankCurrencyRate> preprocessFlux(Stream<MonobankCurrencyRate> flux) {
        return flux
                .filter(rate -> rate.getCurrencyCodeB() == Currency.getInstance("UAH").getNumericCode())
                .filter(rate -> rate.getRateSell() != null && rate.getRateBuy() != null);
    }
}
