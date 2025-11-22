package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerCounterTest {

    PowerCounter app;

    @BeforeEach
    void setUp() {
        app = new PowerCounter();
    }

    @Test
    @DisplayName("shouldCountPowersOfTwoCorrectly")
    void testExampleCase1() {
        int[] readings = {2, 4, 7, 8, 16, 32, 120};
        int k = 2;
        assertEquals(5, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldCountPowersOfLargeNumberCorrectly")
    void testExampleCase2() {
        int[] readings = {10201, 101, 1030301, 101, 101};
        int k = 101;
        assertEquals(5, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldReturnZeroWhenNoPowersPresent")
    void testAllNonPowers() {
        int[] readings = {3, 5, 6, 7, 9};
        int k = 4;
        assertEquals(0, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldCountAllPowersCorrectly")
    void testAllPowers() {
        int[] readings = {1, 3, 9, 27, 81};
        int k = 3;
        assertEquals(5, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldHandleDuplicatePowersCorrectly")
    void testWithDuplicates() {
        int[] readings = {2, 2, 2, 4, 8};
        int k = 2;
        assertEquals(5, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldHandleEmptyArray")
    void testEmptyArray() {
        int[] readings = {};
        int k = 5;
        assertEquals(0, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldHandleKZeroProperly")
    void testWithKZero() {
        int[] readings = {0, 1, 0, 1};
        int k = 0;
        assertEquals(4, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldHandleKOneProperly")
    void testWithKOne() {
        int[] readings = {1, 1, 2, 3};
        int k = 1;
        assertEquals(2, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldHandleLargeNumbersCorrectly")
    void testLargeNumbers() {
        int[] readings = {1, Integer.MAX_VALUE};
        int k = Integer.MAX_VALUE;
        assertEquals(2, app.countPowersOfK(readings, k));
    }

    @Test
    @DisplayName("shouldIgnoreNegativeNumbers")
    void testNegativeNumbers() {
        int[] readings = {-1, -2, -4, 4};
        int k = 2;
        assertEquals(1, app.countPowersOfK(readings, k));
    }
}