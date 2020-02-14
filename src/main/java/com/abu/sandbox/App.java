package com.abu.sandbox;

import com.abu.sandbox.currencysource.api.CurrencyRate;
import com.abu.sandbox.monobank.api.MonobankCurrencyRate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Currency;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        WebClient webClient = WebClient.create("https://api.monobank.ua");
        WebClient.RequestBodySpec request = webClient
                .method(HttpMethod.GET)
                .uri("/bank/currency")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);

        Flux<MonobankCurrencyRate> currencyRateFlux = request.exchange()
                .block()
                .bodyToFlux(MonobankCurrencyRate.class);

        Currency currency = Currency.getInstance("USD");

        CurrencyRate currencyRate =
                preprocessFlux(currencyRateFlux.toStream())
                .filter(rate -> rate.getCurrencyCodeA() == currency.getNumericCode())
                .map(rate -> new CurrencyRate(currency, rate.getRateBuy(), rate.getRateSell(), rate.getDate()))
                .findFirst()
                .orElse(null);

        System.out.println(currencyRate);
    }

    private static Stream<MonobankCurrencyRate> preprocessFlux(Stream<MonobankCurrencyRate> flux) {
        return flux
                .filter(rate -> rate.getCurrencyCodeB() == Currency.getInstance("UAH").getNumericCode())
                .filter(rate -> rate.getRateSell() != null && rate.getRateBuy() != null);
    }
}
