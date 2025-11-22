package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LowestCommonPrefixTest {

    LowestCommonPrefix app;

    @BeforeEach
    void setUp() {
        app = new LowestCommonPrefix();
    }

    @Test
    public void testBruteForceBasicMatch() {
        int[] firstArray = {25, 288, 2655, 54546, 54, 555};
        int[] secondArray = {2, 255, 266, 244, 26, 5, 54547};
        assertEquals(4, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testBruteForceDifferentMatch() {
        int[] firstArray = {25, 288, 2655, 544, 54, 555};
        int[] secondArray = {2, 255, 266, 244, 26, 5, 5444444};
        assertEquals(3, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testBruteForceNoCommonPrefix() {
        int[] firstArray = {817, 99};
        int[] secondArray = {1999, 1909};
        assertEquals(0, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testBruteForceSingleElementEach() {
        int[] firstArray = {12345};
        int[] secondArray = {123};
        assertEquals(3, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testBruteForceEmptyFirstArray() {
        int[] firstArray = {};
        int[] secondArray = {123};
        assertEquals(0, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testBruteForceEmptySecondArray() {
        int[] firstArray = {123};
        int[] secondArray = {};
        assertEquals(0, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testBruteForceIdenticalNumbers() {
        int[] firstArray = {12345};
        int[] secondArray = {12345};
        assertEquals(5, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testBruteForceSameStartDifferentLength() {
        int[] firstArray = {123};
        int[] secondArray = {1234567};
        assertEquals(3, app.findLCP(firstArray, secondArray));
    }

    @Test
    public void testOptimizedBasicMatch() {
        int[] firstArray = {25, 288, 2655, 54546, 54, 555};
        int[] secondArray = {2, 255, 266, 244, 26, 5, 54547};
        assertEquals(4, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    @Test
    public void testOptimizedDifferentMatch() {
        int[] firstArray = {25, 288, 2655, 544, 54, 555};
        int[] secondArray = {2, 255, 266, 244, 26, 5, 5444444};
        assertEquals(3, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    @Test
    public void testOptimizedNoCommonPrefix() {
        int[] firstArray = {817, 99};
        int[] secondArray = {1999, 1909};
        assertEquals(0, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    @Test
    public void testOptimizedSingleElementEach() {
        int[] firstArray = {12345};
        int[] secondArray = {123};
        assertEquals(3, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    @Test
    public void testOptimizedEmptyFirstArray() {
        int[] firstArray = {};
        int[] secondArray = {123};
        assertEquals(0, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    @Test
    public void testOptimizedEmptySecondArray() {
        int[] firstArray = {123};
        int[] secondArray = {};
        assertEquals(0, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    @Test
    public void testOptimizedIdenticalNumbers() {
        int[] firstArray = {12345};
        int[] secondArray = {12345};
        assertEquals(5, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    @Test
    public void testOptimizedSameStartDifferentLength() {
        int[] firstArray = {123};
        int[] secondArray = {1234567};
        assertEquals(3, app.findLongestCommonPrefix(firstArray, secondArray));
    }

    // --- Stress Test (optional, runs fast in Trie) ---
    @Test
    public void testOptimizedLargeInputPerformance() {
        int[] firstArray = new int[10000];
        int[] secondArray = new int[10000];

        Arrays.fill(firstArray, 123456789);
        Arrays.fill(secondArray, 123456700);

        assertEquals(7, app.findLongestCommonPrefix(firstArray, secondArray));
    }
}