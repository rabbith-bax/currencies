package com.interview.Currencies;

import com.interview.Currencies.model.Rate;
import com.interview.Currencies.model.SortedNumbers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;

@RestController
public class RateResource {

    @GetMapping("/status/ping")
    public String pingPong() {
        return "pong";
    }

    @PostMapping("/numbers/sort-command")
    public ResponseEntity<SortedNumbers> sortNumbers(@RequestBody SortedNumbers request) {
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
    public ResponseEntity<Rate> getCurrentCurrencyValue(@RequestBody Rate request) {
        String currencyCode = request.getCode();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode + "/";
        Rate rate = restTemplate.getForObject(url, Rate.class);

        return ResponseEntity.ok(rate);
    }
}
