package com.shortthirdman.primekit.hackerearth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATMPinValidatorTest {

    ATMPinValidator app;

    @BeforeEach
    void setUp() {
        app = new ATMPinValidator();
    }

    @Test
    void testValid4Digit() {
        assertEquals(0, app.validatePin("3814"));
        assertEquals(0, app.validatePin("5029"));
    }

    @Test
    void testValid6Digit() {
        assertEquals(1, app.validatePin("492837"));
        assertEquals(1, app.validatePin("805123"));
    }

    @Test
    void testInvalidLength() {
        assertEquals(2, app.validatePin("123"));
        assertEquals(2, app.validatePin("12345"));
        assertEquals(2, app.validatePin("1234567"));
        assertEquals(2, app.validatePin(""));
        assertEquals(2, app.validatePin(null));
    }

    @Test
    void testNonDigitCharacters() {
        assertEquals(3, app.validatePin("12a4"));
        assertEquals(3, app.validatePin("12 4"));
        assertEquals(3, app.validatePin("12-4"));
        assertEquals(3, app.validatePin("@234"));
    }

    @Test
    void testRepeatedDigits() {
        assertEquals(4, app.validatePin("1123"));
        assertEquals(4, app.validatePin("1213"));
        assertEquals(4, app.validatePin("998877"));
        assertNotEquals(4, app.validatePin("12331"));
    }

    @Test
    void testStrictAscending() {
        assertEquals(5, app.validatePin("1234"));
        assertEquals(5, app.validatePin("2345"));
        assertEquals(5, app.validatePin("345678"));
    }

    @Test
    void testStrictDescending() {
        assertEquals(5, app.validatePin("4321"));
        assertEquals(5, app.validatePin("9876"));
        assertEquals(5, app.validatePin("654321"));
    }

    @Test
    void testMixedSequencesAreValid() {
        // These should be valid 4-digit → return 0
        assertEquals(0, app.validatePin("1357"));
        assertEquals(0, app.validatePin("1256")); // not strictly ascending

        // Valid 6-digit → return 1
        assertEquals(1, app.validatePin("135790"));
        assertEquals(1, app.validatePin("142536"));
    }
}