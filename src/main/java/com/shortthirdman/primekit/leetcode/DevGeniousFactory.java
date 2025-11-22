package com.shortthirdman.primekit.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DevGeniousFactory {
	
	public static int getMaximumDistinctCount(int[] a, int[] b, int k) {
        Map<Integer, Integer> aCount = countFrequencies(a);
        Map<Integer, Integer> bCount = countFrequencies(b);

        int maxDistinctCount = aCount.size();

        for (int i = 0; i < k; i++) {
            int minAElement = aCount.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
            int maxBElement = bCount.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

            if (bCount.get(maxBElement) > aCount.get(minAElement)) {
                aCount.put(minAElement, aCount.get(minAElement) + 1);
                aCount.put(maxBElement, aCount.get(maxBElement) - 1);
                bCount.put(maxBElement, bCount.get(maxBElement) + 1);
                bCount.put(minAElement, bCount.get(minAElement) - 1);

                maxDistinctCount = Math.max(maxDistinctCount, aCount.size());
            }
        }

        return maxDistinctCount;
    }
	
	private static Map<Integer, Integer> countFrequencies(int[] arr) {
        return Arrays.stream(arr).boxed()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
    }
	
	public static List<Integer> evenNumber(List<Integer> list) {
		return list.stream()
				.filter(n -> n % 2 == 0)
				.collect(Collectors.toList());
	}
	
	public static List<Integer> numbersStartingWithOne(List<Integer> list) {
		return list.stream()
                .map(s -> s + "")
                .filter(s -> s.startsWith("1"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
	}
	
	public static Set<Integer> duplicateElements(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalStateException("numbers is null or empty");
        }

		Set<Integer> set = new HashSet<>();
		return list.stream()
				.filter(n -> !set.add(n))
				.collect(Collectors.toSet());
	}
	
	public static int findFirstElement(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalStateException("numbers is null or empty");
        }

		int item = numbers.stream()
                .findFirst()
                .get();
		
		return item;
	}
	
	public static int findMaxElement(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalStateException("numbers is null or empty");
        }

		var max = numbers.stream()
                	.max(Integer::compare)
                	.orElseThrow(() -> new IllegalStateException("No element found"));
		
		return max;
	}
	
	public static Character firstNonRepeatedCharacter(String text) {
        Character result = null;

		if (text == null || text.isEmpty()) {
			return null;
		}
		
		result = text.chars() // Stream of String
	            .mapToObj(s -> Character.toLowerCase((char) s)) // First convert to Character object and then to lowercase
	            .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count 
	            .entrySet()
	            .stream()
	            .filter(entry -> entry.getValue() == 1L)
	            .map(Map.Entry::getKey)
	            .findFirst()
	            .orElseThrow(() -> new IllegalStateException("No non-repeated character found"));
		
		return result;
	}
	
	public static Character firstRepeatedCharacter(String text) {
        Character result = null;

        if (text == null || text.isEmpty()) {
			return null;
		}
		
		result = text.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase((char) s)) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count 
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No repeated character found"));
		
		return result;
	}
	
	public static boolean containsDuplicate(int[] nums) {
		List<Integer> list = Arrays.stream(nums).boxed().toList();
		Set<Integer> set = new HashSet<>(list);

        return set.size() != list.size();
    }
	
	public static Map<String, Long> findCharacterCount(String text) {
        Map<String, Long> countMap = Map.of();
        if (text == null || text.isEmpty()) {
            return null;
        }

		countMap = Arrays.stream(text.split(""))
					.map(String::toLowerCase)
					.collect(Collectors.groupingBy(str -> str, LinkedHashMap::new, Collectors.counting()));

		return countMap;
	}
}
