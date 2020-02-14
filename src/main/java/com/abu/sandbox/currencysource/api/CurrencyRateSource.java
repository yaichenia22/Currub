package com.abu.sandbox.currencysource.api;

import java.util.Currency;
import java.util.List;

public interface CurrencyRateSource {

    CurrencyRate getCurrency(Currency currency);

    List<CurrencyRate> getCurrencies();
}
