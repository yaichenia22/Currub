package com.abu.sandbox.monobank.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

public class MonobankCurrencyRestClient {

    private static final String MONOBANK_API_URL = "https://api.monobank.ua";
    private static final String MONOBANK_CURRENCY_URI = "/bank/currency";

    public ClientResponse getMonoCurrenciesResponse() {

        WebClient webClient = WebClient.create(MONOBANK_API_URL);
        WebClient.RequestBodySpec request = webClient
                .method(HttpMethod.GET)
                .uri(URI.create(MONOBANK_CURRENCY_URI))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);

        return request.exchange()
                .block();
    }
}
