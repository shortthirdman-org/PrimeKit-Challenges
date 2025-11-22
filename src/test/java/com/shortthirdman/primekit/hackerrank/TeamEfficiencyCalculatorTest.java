package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamEfficiencyCalculatorTest {

    TeamEfficiencyCalculator app;

    @BeforeEach
    void setUp() {
        app = new TeamEfficiencyCalculator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testValidInput() {
        // Test with a valid input where pairs satisfy the condition
        List<Integer> skill = Arrays.asList(1, 2, 3, 4);
        long expected = 10; // (1 * 4) + (2 * 3) = 10
        assertEquals(expected, app.getTotalEfficiency(skill));
    }

    @Test
    public void testUnevenParticipants() {
        // Test with an odd number of participants
        List<Integer> skill = Arrays.asList(1, 2, 3);
        assertEquals(-1, app.getTotalEfficiency(skill));
    }

    @Test
    public void testNullInput() {
        // Test with null input
        assertEquals(-1, app.getTotalEfficiency(null));
    }

    @Test
    public void testEmptyInput() {
        // Test with an empty input list
        assertEquals(-1, app.getTotalEfficiency(Arrays.asList()));
    }

    @Test
    public void testInvalidPairing() {
        // Test where pairs cannot form the same sum
        List<Integer> skill = Arrays.asList(1, 2, 3, 6);
        assertEquals(-1, app.getTotalEfficiency(skill));
    }

    @Test
    public void testAllZeroes() {
        // Test where all skills are zero
        List<Integer> skill = Arrays.asList(0, 0, 0, 0);
        long expected = 0; // (0 * 0) + (0 * 0) = 0
        assertEquals(expected, app.getTotalEfficiency(skill));
    }

    @Test
    public void testNegativeSkills() {
        // Test with negative skill values
        List<Integer> skill = Arrays.asList(-1, -2, -3, -4);
        long expected = 10; // (-4 * -1) + (-3 * -2) = 10
        assertEquals(expected, app.getTotalEfficiency(skill));
    }

    @Test
    public void testMixedSkills() {
        // Test with a mix of positive and negative skill values
        List<Integer> skill = Arrays.asList(-1, 1, -2, 2);
        assertEquals(-1, app.getTotalEfficiency(skill)); // Impossible to pair with equal sums
    }

    @Test
    public void testLargeInput() {
        // Test with a large input size
        List<Integer> skill = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80);
        long expected = 2200; // For sorted list, pairing and summing the products satisfies
        assertNotEquals(expected, app.getTotalEfficiency(skill));
        assertEquals(6000, app.getTotalEfficiency(skill));
    }
}