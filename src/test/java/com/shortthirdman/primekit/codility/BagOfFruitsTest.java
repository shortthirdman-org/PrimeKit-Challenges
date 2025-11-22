package com.shortthirdman.primekit.codility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BagOfFruitsTest {

    BagOfFruits app;

    @BeforeEach
    void setUp() {
        app = new BagOfFruits();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void countFruits() {
        List<String> fruits = List.of("apple",
                "apple", "apple", "apple", "apple", "apple",
                "grapes", "grapes", "kiwi", "kiwi", "kiwi", "kiwi", "kiwi");
        List<String> expected = List.of("apple", "kiwi");
        long threshold = 2;
        List<String> actual = app.countFruits(fruits, threshold);
        assertEquals(expected, actual);
    }

    @Test
    void testCountFruits() {
        List<String> fruits = List.of("apple",
                "apple","apple","apple", "apple", "apple",
                "grapes",  "grapes",
                "kiwi","kiwi", "kiwi", "kiwi", "kiwi");
        List<String> expected = List.of("apple", "kiwi");
        int threshold = 2;
        List<String> actual = app.countFruits(fruits, threshold);
        assertEquals(expected, actual);
    }
}