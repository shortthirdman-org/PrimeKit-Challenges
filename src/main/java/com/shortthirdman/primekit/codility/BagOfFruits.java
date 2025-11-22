package com.shortthirdman.primekit.codility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BagOfFruits {

    public List<String> countFruits(List<String> fruits, long threshold) {
        Map<String, Long> countingMap = fruits.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        return countingMap.entrySet()
                .stream()
                .filter(a -> a.getValue() > threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<String> countFruits(List<String> fruits, int threshold) {
        Map<String, Integer> countingMap = new HashMap<>();
        for (String fruit : fruits) {
            countingMap.put(fruit, countingMap.getOrDefault(fruit, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> fruitCountMap : countingMap.entrySet()) {
            if (fruitCountMap.getValue() > threshold) {
                result.add(fruitCountMap.getKey());
            }
        }

        return result;
    }
}
