package com.shortthirdman.primekit.hackerearth;

public class ATMPinValidator {

    public int validatePin(String pin) {
        if (pin == null) return 2;

        int len = pin.length();

        // (2) Invalid length
        if (len != 4 && len != 6) {
            return 2;
        }

        // Convert to int array for easier functional operations
        var digitsOpt = pin.chars()
                .map(c -> c - '0')
                .boxed()
                .toList();

        // (3) Non-digit characters
        if (pin.chars().anyMatch(c -> !Character.isDigit(c))) {
            return 3;
        }

        // (4) Repeated digits
        long distinct = digitsOpt.stream().distinct().count();
        if (distinct != len) {
            return 4;
        }

        // (5) Strict ascending or descending
        boolean asc = true;
        boolean desc = true;

        for (int i = 1; i < len; i++) {
            int diff = digitsOpt.get(i) - digitsOpt.get(i - 1);
            if (diff != 1) asc = false;
            if (diff != -1) desc = false;
        }

        if (asc || desc) {
            return 5;
        }

        // (0) Valid 4-digit
        if (len == 4) return 0;

        // (1) Valid 6-digit
        return 1;
    }
}
