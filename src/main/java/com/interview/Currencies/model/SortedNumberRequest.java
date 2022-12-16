package com.interview.Currencies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SortedNumberRequest {
    private List<Integer> numbers;
    private String order;

    public boolean isAscending() {
        if (getOrder().equals("ASC")) {
            return true;
        } else if (getOrder().equals("DESC")) {
            return false;
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong string for assertions");
        }
    }
}
