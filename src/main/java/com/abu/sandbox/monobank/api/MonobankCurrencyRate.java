package com.abu.sandbox.monobank.api;

public class MonobankCurrencyRate {

    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private Double rateSell;
    private Double rateBuy;
    private Double rateCross;

    public MonobankCurrencyRate() {
    }

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public long getDate() {
        return date;
    }

    public Double getRateSell() {
        return rateSell;
    }

    public Double getRateBuy() {
        return rateBuy;
    }

    public Double getRateCross() {
        return rateCross;
    }

    public void setCurrencyCodeA(int currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public void setCurrencyCodeB(int currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setRateSell(Double rateSell) {
        this.rateSell = rateSell;
    }

    public void setRateBuy(Double rateBuy) {
        this.rateBuy = rateBuy;
    }

    public void setRateCross(Double rateCross) {
        this.rateCross = rateCross;
    }

    @Override
    public String toString() {
        return "MonobankCurrencyRate{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateSell=" + rateSell +
                ", rateBuy=" + rateBuy +
                ", rateCross=" + rateCross +
                '}';
    }
}
