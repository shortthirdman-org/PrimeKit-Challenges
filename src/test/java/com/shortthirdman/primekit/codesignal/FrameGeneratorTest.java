package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameGeneratorTest {

    FrameGenerator app;

    @BeforeEach
    void setUp() {
        app = new FrameGenerator();
    }

    @Test
    void testFrameSize2() {
        String[] expected = {
                "**",
                "**"
        };
        assertArrayEquals(expected, app.generate(2));
    }

    @Test
    void testFrameSize3() {
        String[] expected = {
                "***",
                "* *",
                "***"
        };
        assertArrayEquals(expected, app.generate(3));
    }

    @Test
    void testFrameSize4() {
        String[] expected = {
                "****",
                "*  *",
                "*  *",
                "****"
        };
        assertArrayEquals(expected, app.generate(4));
    }

    @Test
    void testFrameSize8() {
        String[] expected = {
                "********",
                "*      *",
                "*      *",
                "*      *",
                "*      *",
                "*      *",
                "*      *",
                "********"
        };
        assertArrayEquals(expected, app.generate(8));
    }

    @Test
    void testFrameSize10() {
        String[] result = app.generate(10);
        assertEquals(10, result.length, "Number of rows should match n");

        for (int i = 0; i < 10; i++) {
            assertEquals(10, result[i].length(), "Each row should have n characters");
            if (i == 0 || i == 9) {
                assertEquals("*".repeat(10), result[i], "Top and bottom rows must be full of *");
            } else {
                assertEquals("*" + " ".repeat(8) + "*", result[i], "Middle rows should have * at edges and spaces inside");
            }
        }
    }

    @Test
    void testIllegalArgumentForNEquals1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.generate(1);
        });
        assertEquals("Frame size must be at least 2.", exception.getMessage());
    }

    @Test
    void testIllegalArgumentForNegativeN() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.generate(-5);
        });
        assertEquals("Frame size must be at least 2.", exception.getMessage());
    }

    @Test
    void testIllegalArgumentForZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.generate(0);
        });
        assertEquals("Frame size must be at least 2.", exception.getMessage());
    }
}