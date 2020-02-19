package com.abu.sandbox.monobank.api;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class MonobankCurrencyRestClientTest {

    private static final Logger logger = LoggerFactory.getLogger(MonobankCurrencyRestClientTest.class);

    /*
    * Needs firewall free Internet connection
    * */
    @Test
    public void testGetMonoCurrenciesResponse() {
        MonobankCurrencyRestClient client = new MonobankCurrencyRestClient();
        Flux<MonobankCurrencyRate> responseStringMono = client.getMonoCurrenciesRates();
        responseStringMono.toIterable().forEach(it -> logger.info(it.toString()));
    }
}
