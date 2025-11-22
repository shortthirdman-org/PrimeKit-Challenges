package com.shortthirdman.primekit.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberConverter {

    private static final Map<Character, Integer> ROMAN_TO_INT = new HashMap<>();
    private static final Map<Integer, String> INT_TO_ROMAN = new HashMap<>();

    static {
        ROMAN_TO_INT.put('I', 1);
        ROMAN_TO_INT.put('V', 5);
        ROMAN_TO_INT.put('X', 10);
        ROMAN_TO_INT.put('L', 50);
        ROMAN_TO_INT.put('C', 100);
        ROMAN_TO_INT.put('D', 500);
        ROMAN_TO_INT.put('M', 1000);

        INT_TO_ROMAN.put(1, "I");
        INT_TO_ROMAN.put(4, "IV");
        INT_TO_ROMAN.put(5, "V");
        INT_TO_ROMAN.put(9, "IX");
        INT_TO_ROMAN.put(10, "X");
        INT_TO_ROMAN.put(40, "XL");
        INT_TO_ROMAN.put(50, "L");
        INT_TO_ROMAN.put(90, "XC");
        INT_TO_ROMAN.put(100, "C");
        INT_TO_ROMAN.put(400, "CD");
        INT_TO_ROMAN.put(500, "D");
        INT_TO_ROMAN.put(900, "CM");
        INT_TO_ROMAN.put(1000, "M");
    }

    public int romanToInteger(String s) {
        if (s == null) {
            throw new NullPointerException("Input roman number can not be null");
        }

        validateRomanNumeral(s);

        int result = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = ROMAN_TO_INT.get(s.charAt(i));

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        return result;

    }

    public String integerToRoman(int number) {
        if (number <= 0 || number >= 4000) {
            throw new IllegalArgumentException("Numbers less than zero (0) or more than 3999 can not be converted to Roman numeral");
        }
        StringBuilder result = new StringBuilder();

        while (number > 0) {
            int largestDivisor = findLargestDivisor(number);
            result.append(INT_TO_ROMAN.get(largestDivisor));
            number -= largestDivisor;
        }

        return result.toString();
    }

    private int findLargestDivisor(int num) {
        return INT_TO_ROMAN.keySet().stream()
                .filter(divisor -> num >= divisor)
                .max(Integer::compare)
                .orElse(0);
    }

    private void validateRomanNumeral(String s) {
        // Check if the input contains valid Roman numeral characters
        for (char c : s.toCharArray()) {
            if (!ROMAN_TO_INT.containsKey(c)) {
                throw new IllegalArgumentException("Invalid Roman numeral character: " + c);
            }
        }

        // Check for invalid repetition patterns
        if (s.contains("IIII") || s.contains("VV") || s.contains("XXXX") ||
                s.contains("LL") || s.contains("CCCC") || s.contains("DD") || s.contains("MMMM")) {
            throw new IllegalArgumentException("Invalid Roman numeral sequence: " + s);
        }

        // Check for invalid subtractive combinations
        if (s.contains("IL") || s.contains("IC") || s.contains("ID") || s.contains("IM") ||
                s.contains("VX") || s.contains("VL") || s.contains("VC") || s.contains("VD") || s.contains("VM") ||
                s.contains("XD") || s.contains("XM") ||
                s.contains("LC") || s.contains("LD") || s.contains("LM") ||
                s.contains("DM")) {
            throw new IllegalArgumentException("Invalid Roman numeral sequence: " + s);
        }
    }
}
