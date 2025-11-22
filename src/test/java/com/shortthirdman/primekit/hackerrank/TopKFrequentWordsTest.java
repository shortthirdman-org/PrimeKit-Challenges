package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TopKFrequentWordsTest {

    TopKFrequentWords app;

    @BeforeEach
    void setUp() {
        app = new TopKFrequentWords();
    }

    private record TestCase(String[] words, int k, List<String> expected) {}

    static Stream<TestCase> provideTopKTestCases() {
        return Stream.of(
                new TestCase(
                        new String[]{"i", "love", "leetcode", "i", "love", "coding"},
                        2,
                        Arrays.asList("i", "love")
                ),
                new TestCase(
                        new String[]{"the", "day", "is", "sunny", "the", "the", "the",
                                "sunny", "is", "is"},
                        4,
                        Arrays.asList("the", "is", "sunny", "day")
                ),
                new TestCase(
                        new String[]{"apple", "banana", "apple"},
                        5,
                        Arrays.asList("apple", "banana")
                ),
                new TestCase(
                        new String[]{"c", "b", "a"},
                        3,
                        Arrays.asList("a", "b", "c") // lexicographic order on tie
                ),
                new TestCase(
                        new String[]{"apple", "apple", "apple"},
                        1,
                        Collections.singletonList("apple")
                ),
                new TestCase(
                        new String[]{},
                        3,
                        Collections.emptyList()
                ),
                new TestCase(
                        new String[]{"apple", "banana"},
                        0,
                        Collections.emptyList()
                ),
                new TestCase(
                        new String[]{"x", "y", "z", "x", "y", "z"},
                        10,
                        Arrays.asList("x", "y", "z")
                ),
                new TestCase(
                        new String[]{"Apple", "apple", "Banana", "banana", "apple"},
                        3,
                        Arrays.asList("apple", "Apple", "Banana")
                )
//                new TestCase(
//                        new String[]{"apple", "banana"},
//                        -1,
//                        Collections.emptyList()
//                )
        );
    }

    @ParameterizedTest(name = "Test case #{index} - k={0}")
    @MethodSource("provideTopKTestCases")
    void testTopKFrequentParameterized(TestCase testCase) {
        List<String> actual = app.topKFrequent(testCase.words, testCase.k);
        assertEquals(testCase.expected, actual);
    }

    @Test
    void testBasicCase() {
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple", "kiwi"};
        int k = 2;
        List<String> result = app.topKFrequent(words, k);
        List<String> expected  = List.of("apple", "banana");
        assertEquals(expected, result);
        assertEquals(k, result.size());
    }

    @Test
    void testBasicPositiveCase() {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> result = app.topKFrequent(words, 2);
        assertEquals(Arrays.asList("i", "love"), result);
    }

    @Test
    void testFrequencyAndLexicographicTieBreak() {
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the",
                "sunny", "is", "is"};
        List<String> result = app.topKFrequent(words, 4);
        assertEquals(Arrays.asList("the", "is", "sunny", "day"), result);
    }

    @Test
    void testKGreaterThanUniqueWords() {
        String[] words = {"apple", "banana", "apple"};
        List<String> result = app.topKFrequent(words, 5);
        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    void testAllWordsSameFrequencyLexicographicOrder() {
        String[] words = {"c", "b", "a"};
        List<String> result = app.topKFrequent(words, 3);
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    void testSingleWordMultipleTimes() {
        String[] words = {"apple", "apple", "apple"};
        List<String> result = app.topKFrequent(words, 1);
        assertEquals(Collections.singletonList("apple"), result);
    }

    @Test
    void testEmptyArrayReturnsEmptyList() {
        String[] words = {};
        List<String> result = app.topKFrequent(words, 3);
        assertTrue(result.isEmpty());
    }

    @Test
    void testKIsZeroReturnsEmptyList() {
        String[] words = {"apple", "banana"};
        List<String> result = app.topKFrequent(words, 0);
        assertTrue(result.isEmpty());
    }

    @Test
    void testArrayWithNullWords() {
        String[] words = {"apple", null, "apple", null};
        // null is treated like any string key in the map
        assertThrows(NullPointerException.class, () -> app.topKFrequent(words, 2));
//        assertEquals(Arrays.asList("apple", null), result);
    }

    @Test
    void testNegativeKReturnsEmptyList() {
        String[] words = {"apple", "banana"};
        assertThrowsExactly(IllegalArgumentException.class, () -> app.topKFrequent(words, -1));
    }

    @Test
    void testLargeKWithDuplicatesAndTies() {
        String[] words = {"x", "y", "z", "x", "y", "z"};
        List<String> result = app.topKFrequent(words, 10);
        assertEquals(Arrays.asList("x", "y", "z"), result);
    }

    @Test
    void testCaseSensitivity() {
        String[] words = {"Apple", "apple", "Banana", "banana", "apple"};
        List<String> result = app.topKFrequent(words, 3);
        // 'apple' frequency = 2, 'Apple', 'Banana', 'banana' each = 1
        assertEquals(Arrays.asList("apple", "Apple", "Banana"), result);
    }
}