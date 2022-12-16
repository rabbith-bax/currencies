package com.interview.Currencies;

import com.google.gson.Gson;
import com.interview.Currencies.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
public class RateResource {

    @GetMapping("/status/ping")
    public String pingPong() {
        return "pong";
    }

    @PostMapping("/numbers/sort-command")
    public ResponseEntity<SortedNumbers> sortNumbers(@RequestBody SortedNumberRequest request) {
        List<Integer> numbers = request.getNumbers();
        boolean ascending = request.isAscending();

        if (ascending) {
            numbers.sort(Comparator.naturalOrder());
        } else {
            numbers.sort(Comparator.reverseOrder());
        }

        return ResponseEntity.ok(new SortedNumbers(numbers));
    }

    @PostMapping("/currencies/get-current-currency-value-command")
    public ResponseEntity<Double> getCurrencyRate(@RequestBody RateRequest request) throws IOException{
        String currencyCode = request.getCode();
        List<Rate> rates = Arrays
                .stream(getNBPResponse())
                .findFirst()
                .orElse(new NBPResponse())
                .getRates();
        Rate rate = rates
                .stream()
                .filter(r -> r
                        .getCode()
                        .equals(currencyCode))
                .findFirst()
                .orElse(new Rate());

        return ResponseEntity.ok(rate.getMid());
    }

    private static NBPResponse[] getNBPResponse() throws IOException {
        URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A?format=json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        Gson gson = new Gson();
        return gson.fromJson(reader, NBPResponse[].class);
    }
}