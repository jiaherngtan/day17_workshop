package com.vttp2022.workshop17.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vttp2022.workshop17.model.Currency;

@Service
public class CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    // https://api.apilayer.com/fixer/latest?base=USD&symbols=MYR&apikey=J4Wbz9Hhh2CuNtn53dWSonZMCT1WhQpI
    public static String URL = "https://api.apilayer.com/fixer/latest";

    @Value("${apiKey}")
    private String apiKey;

    private boolean hasKey;

    @PostConstruct
    private void init() {
        hasKey = null != apiKey;
        logger.info("|| HAS KEY?: " + hasKey + " ||");
    }

    public Optional<Currency> getCurrencyExchange(String fromCurrency, String toCurrency, String amount) {
        String currencyUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("base", fromCurrency)
                .queryParam("symbols", toCurrency)
                .queryParam("apiKey", apiKey)
                .toUriString();

        logger.info("|| URL: " + currencyUrl + " ||");

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try {
            resp = template.getForEntity(currencyUrl, String.class);
            Currency c = Currency.create(resp.getBody(), toCurrency);
            return Optional.of(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
