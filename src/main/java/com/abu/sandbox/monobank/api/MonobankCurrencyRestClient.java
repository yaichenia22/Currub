package com.abu.sandbox.monobank.api;

import com.abu.sandbox.errors.MonobankBadStatusException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MonobankCurrencyRestClient {

    private static final String MONOBANK_API_URL = "https://api.monobank.ua";
    private static final String MONOBANK_CURRENCY_URI = "/bank/currency";

    public Flux<MonobankCurrencyRate> getMonoCurrenciesRates() {

        WebClient webClient = WebClient.create(MONOBANK_API_URL);
        WebClient.RequestBodySpec request = webClient
                .method(HttpMethod.GET)
                .uri(MONOBANK_CURRENCY_URI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);

        return request
                .retrieve()
                .onStatus(HttpStatus::isError, this::handleError)
                .bodyToFlux(MonobankCurrencyRate.class);
    }

    private Mono<? extends Throwable> handleError(ClientResponse clientResponse) {
        HttpStatus status = clientResponse.statusCode();
        return Mono.just(
                new MonobankBadStatusException(String.format(
                        "Monobank response has bad status: code [%s], reason [%s]",
                        status.value(),
                        status.getReasonPhrase())));
    }
}
