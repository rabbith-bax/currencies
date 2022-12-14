package com.interview.Currencies.model;

import java.io.Serializable;
import java.util.List;

public class SortedNumbers implements Serializable {
    private List<Integer> numbers;
    private boolean isAscending;

    public SortedNumbers() {
    }

    public SortedNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public SortedNumbers(List<Integer> numbers, boolean isAscending) {
        this.numbers = numbers;
        this.isAscending = isAscending;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    @Override
    public String toString() {
        return "SortedNumbers{" +
                "numbers=" + numbers +
                ", isAscending=" + isAscending +
                '}';
    }
}
