package com.vttp2022.workshop17.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Currency {

    private static final Logger logger = LoggerFactory.getLogger(Currency.class);

    private String fromCurrency;
    private String toCurrency;
    private int amount;
    private String exchangeRate;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public static Currency create(String json, String toCurrency) throws IOException {
        Currency c = new Currency();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader jr = Json.createReader(is);
            JsonObject jo = jr.readObject();
            c.fromCurrency = jo.getString("base");
            logger.info("|| Base Currency: " + c.getFromCurrency() + " ||");
            JsonObject obj = jo.getJsonObject("rates");
            c.exchangeRate = obj.getJsonNumber(toCurrency).toString();
        }
        return c;
    }

    public void convert() {

    }
}
