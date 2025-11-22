package com.shortthirdman.primekit.codesignal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * You are working with data collected from various sensors. Given an array of non-negative integers readings representing the sensor readings,
 * transform the array by repeatedly replacing each element with the sum of its digits. Continue this transformation until
 * every element is a single digit. Return the most occurring digit in the final array. In case of a tie, return the highest digit.
 *
 * @author ShortThirdMan
 */
public class SensorDigitAnalyzer {

    /**
     * @param readings the energy power readings
     * @return the most frequent digit
     */
    public int findMostFrequentSingleDigit(int[] readings) {
        if (readings == null || readings.length == 0) {
            return -1;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int reading : readings) {
            int singleDigit = reduceToSingleDigit(reading);
            freqMap.put(singleDigit, freqMap.getOrDefault(singleDigit, 0) + 1);
        }

        int maxFrequency = 0;
        int mostFrequentDigit = -1;

        for (int digit = 0; digit <= 9; digit++) {
            int frequency = freqMap.getOrDefault(digit, 0);
            if (frequency > maxFrequency || (frequency == maxFrequency && digit > mostFrequentDigit)) {
                maxFrequency = frequency;
                mostFrequentDigit = digit;
            }
        }

        return mostFrequentDigit;
    }

    private int reduceToSingleDigit(int num) {
        while (num >= 10) {
            num = String.valueOf(num)
                    .chars()
                    .map(Character::getNumericValue)
                    .sum();
        }
        return num;
    }

    public int findMostFrequentSingleDigit(List<Integer> readings) {
        Map<Integer, Long> freqMap = readings.stream()
                .map(this::reduceToSingleDigit)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return freqMap.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int freqCompare = Long.compare(b.getValue(), a.getValue());
                    if (freqCompare != 0) {
                        return freqCompare;
                    } else {
                        return Integer.compare(b.getKey(), a.getKey());
                    }
                })
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(-1);
    }
}
