package com.shortthirdman.primekit.hackerrank;

import com.shortthirdman.primekit.common.EmployeeRecord;
import com.shortthirdman.primekit.common.Transaction;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(InstancioExtension.class)
class StreamApiFactoryTest {

    StreamApiFactory app;

    @BeforeEach
    void setUp() {
        app = new StreamApiFactory();
    }

    @Test
    void concatenateEvenLengthWords() {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        String actualWords = app.concatenateEvenLengthWords(words);
        assertNotEquals("bananacherrydateelderberry", actualWords);
        assertEquals("bananacherry", actualWords);
        assertEquals("bananacherry".length(), actualWords.length());

        assertThrows(IllegalArgumentException.class, () -> app.concatenateEvenLengthWords(List.of()));
        assertThrows(IllegalArgumentException.class, () -> app.concatenateEvenLengthWords(null));

        words = Arrays.asList("a", "abc", "def");
        assertEquals("", app.concatenateEvenLengthWords(words));

        words = List.of("test");
        assertEquals("test", app.concatenateEvenLengthWords(words));
    }

    @Test
    void countCharacterFrequency() {
        String str =  "Now is the winter";
        var actualCharacterFrequency = app.countCharacterFrequency(str);
        var expectedCharacterFrequency = Map.ofEntries(
                Map.entry(" ", 3L),
                Map.entry("r", 1L),
                Map.entry("s", 1L),
                Map.entry("t", 2L),
                Map.entry("e", 2L),
                Map.entry("w", 2L),
                Map.entry("h", 1L),
                Map.entry("i", 2L),
                Map.entry("n", 1L),
                Map.entry("N", 1L),
                Map.entry("o", 1L)
        );
        assertEquals(expectedCharacterFrequency.size(), actualCharacterFrequency.size());
        assertEquals(expectedCharacterFrequency.keySet(), actualCharacterFrequency.keySet());
        assertEquals(expectedCharacterFrequency, actualCharacterFrequency);
        assertThrows(IllegalArgumentException.class, () -> app.countCharacterFrequency(null));
        assertThrows(IllegalArgumentException.class, () -> app.countCharacterFrequency(""));
    }

    @Test
    void getDailyTransactionSums() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300)
        );
        Map<String, Long> actualDailyTotals = app.getDailyTransactionSums(transactions);
        Map<String, Long> expectedDailyTotals = Map.ofEntries(
                Map.entry("2022-01-01", 300L),
                Map.entry("2022-01-02", 300L)
        );
        assertEquals(expectedDailyTotals, actualDailyTotals);

        transactions = Instancio.createList(Transaction.class);
        assertFalse(transactions.isEmpty());
    }

    @Test
    void toSortedMapWithDuplicates() {
        List<EmployeeRecord> employees = Arrays.asList(
                new EmployeeRecord(101, "Alice"),
                new EmployeeRecord(102, "Bob"),
                new EmployeeRecord(101, "Charlie"),
                new EmployeeRecord(103, "David"),
                new EmployeeRecord(102, "Eve")
        );
        Map<Integer, List<EmployeeRecord>> expectedEmployeeMap = Map.ofEntries(
                Map.entry(101, List.of(new EmployeeRecord(101, "Alice"), new EmployeeRecord(101, "Charlie"))),
                Map.entry(102, List.of(new  EmployeeRecord(102, "Bob"), new EmployeeRecord(102, "Eve"))),
                Map.entry(103, List.of(new EmployeeRecord(103, "David")))
        );
        Map<Integer, List<EmployeeRecord>> actualEmployeeMap = app.toSortedMapWithDuplicates(employees);
        assertEquals(expectedEmployeeMap.size(), actualEmployeeMap.size());
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        assertThrows(IllegalArgumentException.class, () -> app.toSortedMapWithDuplicates(null));
        assertThrows(IllegalArgumentException.class, () -> app.toSortedMapWithDuplicates(List.of()));
    }

    @Test
    void countStringsByFirstCharacter() {
        List<String> words = Arrays.asList("apple", "banana", "bear", "cat", "apple");
        Map<Character, Long> expectedFrequencies = Map.ofEntries(
                Map.entry('a', 2L),
                Map.entry('b', 2L),
                Map.entry('c', 1L)
        );
        Map<Character, Long> actualFrequencies = app.countStringsByFirstCharacter(words);
        assertEquals(expectedFrequencies, actualFrequencies);
        assertEquals(expectedFrequencies.size(), actualFrequencies.size());
        assertThrows(IllegalArgumentException.class, () -> app.countStringsByFirstCharacter(null));
        assertThrows(IllegalArgumentException.class, () -> app.countStringsByFirstCharacter(List.of()));
    }

    @Test
    void mergeSortAndFilterByThreshold() {
        int[] array1 = {1, 5, 3, 9, 7};
        int[] array2 = {2, 4, 6, 8, 10};
        int threshold = 7;
        var actualIntegers = app.mergeSortAndFilterByThreshold(array1, array2, threshold);
        var expectedIntegers = Arrays.asList(8, 9, 10);
        assertEquals(expectedIntegers, actualIntegers);

        assertThrows(IllegalArgumentException.class, () -> app.mergeSortAndFilterByThreshold(array1, null, 7));
        assertThrows(IllegalArgumentException.class, () -> app.mergeSortAndFilterByThreshold(null, null, 7));
    }

    @Test
    void partitionByPrime() {
        var numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10);
        var actualPartitionByPrime = app.partitionByPrime(numbers);
        var expectedPartitionByPrime = Map.ofEntries(
                Map.entry(true, List.of(2, 3, 5, 7)),
                Map.entry(false, List.of(4, 6, 8, 9, 10))
        );
        assertEquals(expectedPartitionByPrime.size(), actualPartitionByPrime.size());
        assertEquals(expectedPartitionByPrime.get(true).size(), actualPartitionByPrime.get(true).size());
        assertEquals(expectedPartitionByPrime.get(false).size(), actualPartitionByPrime.get(false).size());
        assertEquals(expectedPartitionByPrime, actualPartitionByPrime);
        assertThrows(IllegalArgumentException.class, () -> app.partitionByPrime(null));
        assertThrows(IllegalArgumentException.class, () -> app.partitionByPrime(List.of()));
    }

    @Test
    void countTotalUniqueWords() {
        var sentences = Arrays.asList(
                "Java Stream API provides a fluent interface",
                "It supports functional-style operations on streams",
                "In this exercise, you need to count words"
        );
    }
}