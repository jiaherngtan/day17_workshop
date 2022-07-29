package com.vttp2022.workshop17.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vttp2022.workshop17.model.Currency;
import com.vttp2022.workshop17.service.CurrencyService;

@Controller
@RequestMapping(path = "/currency")
public class CurrencyController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService cs;

    @GetMapping
    public String getPage(@RequestParam(required = true) String fromCurrency,
            String toCurrency, String amount, Model model) {
        Optional<Currency> opt = cs.getCurrencyExchange(fromCurrency, toCurrency, amount);
        if (opt.isEmpty()) {
            return "result";
        }
        model.addAttribute("currency", opt.get());
        return "result";
    }
}

// movie app

// CREATE - save new movie into database
// READ - retrieve the movie from database
// UPDATE - update the rating / comment of movie on database
// DELETE - delete the movie

// calling movie api