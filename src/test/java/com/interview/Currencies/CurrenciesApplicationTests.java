package com.interview.Currencies;

import com.interview.Currencies.model.RateRequest;
import com.interview.Currencies.model.SortedNumberRequest;
import com.interview.Currencies.model.SortedNumbers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrenciesApplicationTests {

    @Test
    public void testPingPong() {
        RateResource controller = new RateResource();
        String result = controller.pingPong();
        assertEquals("pong", result);
    }

    @Test
    public void testSortNumbersAscending() {
        RateResource controller = new RateResource();
        SortedNumberRequest request = new SortedNumberRequest(
                new ArrayList<>(List.of(5, 2, 3, 1, 4)), "asc");
        ResponseEntity<SortedNumbers> response = controller.sortNumbers(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(1, 2, 3, 4, 5), Objects.requireNonNull(response.getBody()).getNumbers());
    }

    @Test
    public void testSortNumbersDescending() {
        RateResource controller = new RateResource();
        SortedNumberRequest request = new SortedNumberRequest(
                new ArrayList<>(List.of(5, 2, 3, 1, 4)), "desc");
        ResponseEntity<SortedNumbers> response = controller.sortNumbers(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(5, 4, 3, 2, 1), Objects.requireNonNull(response.getBody()).getNumbers());
    }

    @Test
    public void testSortNumbersBadRequest() {
        RateResource controller = new RateResource();
        SortedNumberRequest request = new SortedNumberRequest(
                new ArrayList<>(List.of(5, 2, 3, 1, 4)), "xddd");
        assertThrows(ResponseStatusException.class, () -> controller.sortNumbers(request));
    }

    @Test
    public void testGetCurrencyRate() throws IOException {
        RateResource controller = new RateResource();
        RateRequest request = new RateRequest("USD");
        ResponseEntity<Double> response = controller.getCurrencyRate(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(4.37, response.getBody());
    }

    @Test
    public void testGetCurrencyRateNotFound() throws IOException {
        RateResource controller = new RateResource();
        RateRequest request = new RateRequest("XDD");
        ResponseEntity<Double> response = controller.getCurrencyRate(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}