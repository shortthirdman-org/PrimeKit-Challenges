package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StudentChalkReplacerTest {

    StudentChalkReplacer app;
    int[] chalk;
    int k;

    @BeforeEach
    void setUp() {
        app = new StudentChalkReplacer();
    }

    @Test
    public void testBasicFunctionality1() {
        chalk = new int[] {5, 1, 5};
        k = 22;
        assertEquals(0, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testBasicFunctionality2() {
        chalk = new int[] {3, 4, 1, 2};
        k = 25;
        assertEquals(1, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testSingleStudentEnoughChalk() {
        chalk = new int[] {1};
        k = 1;
        assertEquals(0, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testSingleStudentExactChalk() {
        chalk = new int[] {5};
        k = 5;
        assertEquals(0, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testSingleStudentExcessChalk() {
        chalk = new int[] {5};
        k = 10;
        assertEquals(0, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testExactChalkPerRound() {
        chalk = new int[] {1, 2, 3};
        k = 6;
        assertEquals(0, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testKLessThanFirstStudentRequirement() {
        chalk = new int[] {2, 4, 1};
        k = 1;
        assertEquals(0, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testLargeKValue() {
        chalk = new int[] {100000, 100000, 100000};
        k = 1000000000;
        assertEquals(1, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testLargeNumberOfStudents() {
        chalk = new int[100000];
        Arrays.fill(chalk, 1);
        k = 99999;
        assertEquals(99999, app.chalkReplacer(chalk, k));
    }

    @Test
    public void testNullChalkArray() {
        chalk = null;
        k = 10;
        assertThrows(NullPointerException.class, () -> app.chalkReplacer(chalk, k));
    }

    @Test
    public void testEmptyChalkArray() {
        chalk = new int[] {};
        k = 10;
        assertThrows(IllegalArgumentException.class, () -> app.chalkReplacer(chalk, k));
    }

    @Test
    public void testNegativeChalkValues() {
        chalk = new int[] {-1, 2, 3};
        k = 4;
        assertThrows(IllegalArgumentException.class, () -> app.chalkReplacer(chalk, k));
    }

    @Test
    public void testNegativeKValue() {
        chalk = new int[] {1, 2, 3};
        k = -1;
        assertThrows(IllegalArgumentException.class, () -> app.chalkReplacer(chalk, k));
    }

    @Test
    public void testMaximumValues() {
        chalk = new int[] {100000, 100000, 100000};
        k = 999999999;
        assertNotEquals(2, app.chalkReplacer(chalk, k));
        assertEquals(0, app.chalkReplacer(chalk, k));
    }
}