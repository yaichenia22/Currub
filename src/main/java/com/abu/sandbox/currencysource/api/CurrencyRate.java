package com.abu.sandbox.currencysource.api;

import java.util.Currency;

public class CurrencyRate {

    private Currency currency;
    private double rateBuy;
    private double rateSell;
    private long date;

    public CurrencyRate(Currency currency, double rateBuy, double rateSell, long date) {
        this.currency = currency;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
        this.date = date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getRateBuy() {
        return rateBuy;
    }

    public double getRateSell() {
        return rateSell;
    }

    public long getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "currency=" + currency +
                ", rateBuy=" + rateBuy +
                ", rateSell=" + rateSell +
                ", date=" + date +
                '}';
    }
}
