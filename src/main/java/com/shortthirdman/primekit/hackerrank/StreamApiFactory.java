package com.shortthirdman.primekit.hackerrank;

import com.shortthirdman.primekit.common.EmployeeRecord;
import com.shortthirdman.primekit.common.Transaction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiFactory {

    public String concatenateEvenLengthWords(List<String> words) {
        String result = null;
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("words cannot be null or empty");
        }

        result = words.stream()
                .filter(x -> x.length() % 2 == 0)
                .limit(2)
                .collect(Collectors.joining());
        return result;
    }

    /**
     * @param sentence the sentence or group of words
     * @return map of character frequency
     */
    public Map<String, Long> countCharacterFrequency(String sentence) {
        Map<String, Long> charFreq = null;

        if (sentence == null || sentence.isEmpty()) {
            throw new IllegalArgumentException("sentence cannot be null or empty");
        }

        charFreq = Arrays.stream(sentence.split("")).collect(
                Collectors.groupingBy(
                        Function.identity(), // or x -> x
                        Collectors.counting()
                )
        );

        return charFreq;
    }

    /**
     * @param transactions the list of transactions
     * @return
     */
    public Map<String, Long> getDailyTransactionSums(List<Transaction> transactions) {
        Map<String, Long> dailyTotals = null;

        if (transactions == null || transactions.isEmpty()) {
            throw new IllegalArgumentException("transactions cannot be null or empty");
        }

        dailyTotals = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getDate,
                        TreeMap::new,
                        Collectors.summingLong(Transaction::getAmount)
                ));

        return dailyTotals;
    }

    /**
     * @param employees the list of employees with identifier and name
     * @return
     */
    public Map<Integer, List<EmployeeRecord>> toSortedMapWithDuplicates(List<EmployeeRecord> employees) {
        Map<Integer, List<EmployeeRecord>> employeeMap = null;

        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("employees cannot be null or empty");
        }

        employeeMap = employees.stream()
                .collect(Collectors.groupingBy(
                        EmployeeRecord::getId,         // Key extractor
                        TreeMap::new,            // Use TreeMap to maintain sorted order
                        Collectors.toList()      // Collect values into a list
                ));

        return employeeMap;
    }

    public Map<Character, Long> countStringsByFirstCharacter(List<String> words) {
        Map<Character, Long> frequency = null;

        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("words cannot be null or empty");
        }

        frequency = words.stream()
                .collect(Collectors.groupingBy(
                        str -> str.charAt(0),
                        Collectors.counting()
                ));

        return frequency;
    }

    /**
     * Given two arrays of integers, merge them, sort them, and then
     * filter out any numbers greater than a specified threshold.
     *
     * @param arr1 the first input array
     * @param arr2 the second input array
     * @param threshold the threshold value
     * @return the list of concatenated array of integers
     */
    public List<Integer> mergeSortAndFilterByThreshold(int[] arr1,  int[] arr2, int threshold) {
        Stream<Integer> intStream = null;
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
            throw new IllegalArgumentException("arr1 and arr2 cannot be null or empty");
        }

        intStream = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .boxed()
                .sorted(Comparator.naturalOrder())
                .filter(x -> x > threshold);

        return intStream.collect(Collectors.toList());
    }

    /**
     * @param numbers the list of numbers
     * @return
     */
    public Map<Boolean, List<Integer>> partitionByPrime(List<Integer> numbers) {
        Map<Boolean, List<Integer>> partitioned = null;
        if  (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("numbers cannot be null or empty");
        }

        partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(this::isPrime));

        return partitioned;
    }

    private boolean isPrime(int number) {
        return number > 1 && IntStream.range(2, (int) Math.sqrt(number) + 1).noneMatch(i -> number % i == 0);
    }

    /**
     * Count the total number of distinct words (case-insensitive) across multiple sentences.
     * @param sentences the input list of group of words
     * @return
     */
    public long countTotalUniqueWords(List<String> sentences) {
        if (sentences == null || sentences.isEmpty()) {
            throw new IllegalArgumentException("sentences cannot be null or empty");
        }
        var uniqueWords = sentences.stream()
                .map(x -> x.toLowerCase().split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .count();
        return uniqueWords;
    }
}
