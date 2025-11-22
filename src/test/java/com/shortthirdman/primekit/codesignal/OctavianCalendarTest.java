package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OctavianCalendarTest {

    OctavianCalendar app;

    @BeforeEach
    void setUp() {
        app = new OctavianCalendar();
    }

    @Test
    @DisplayName("Standard valid input - basic forward phase shift")
    void testStandardCase() {
        assertEquals("Twilight", app.computeOctavianLunarPhase("January", 4, "Full"));
    }

    @Test
    @DisplayName("Cycle wraps around correctly")
    void testPhaseWrapAround() {
        assertEquals("NewMoon", app.computeOctavianLunarPhase("January", 9, "NewMoon"));
    }

    @Test
    @DisplayName("Multi-season offset works correctly")
    void testMultiSeasonCalculation() {
        assertEquals("Gibbous", app.computeOctavianLunarPhase("February", 4, "Crescent"));
    }

    @Test
    @DisplayName("First day of the year")
    void testFirstDayOfYear() {
        assertEquals("NewMoon", app.computeOctavianLunarPhase("January", 1, "NewMoon"));
    }

    @Test
    @DisplayName("Last day of the year")
    void testLastDayOfYear() {
        assertNotEquals("Twilight", app.computeOctavianLunarPhase("December", 31, "NewMoon"));
        assertEquals("Full", app.computeOctavianLunarPhase("December", 31, "NewMoon"));
    }

    @Test
    @DisplayName("End of February (non-leap year)")
    void testEndOfFebruary() {
        assertEquals("Quarter", app.computeOctavianLunarPhase("February", 28, "NewMoon"));
    }

    // ---------- ❌ Negative Test Cases ----------

    @Test
    @DisplayName("Invalid season name throws exception")
    void testInvalidSeason() {
        assertThrows(IllegalArgumentException.class, () ->
                app.computeOctavianLunarPhase("Octember", 10, "NewMoon")
        );
    }

    @Test
    @DisplayName("Invalid phase name throws exception")
    void testInvalidPhase() {
        assertThrows(IllegalArgumentException.class, () ->
                app.computeOctavianLunarPhase("January", 5, "DarkMoon")
        );
    }

    @Test
    @DisplayName("Day count exceeds maximum for season")
    void testDayExceedsSeasonLimit() {
        assertThrows(IllegalArgumentException.class, () ->
                app.computeOctavianLunarPhase("February", 30, "Full")
        );
    }

    @Test
    @DisplayName("Day count is less than 1")
    void testDayBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () ->
                app.computeOctavianLunarPhase("March", 0, "Full")
        );
    }

    // ---------- 🧪 Edge/Boundary Test Cases ----------

    @Test
    @DisplayName("Minimum valid day (1)")
    void testMinimumDay() {
        assertNotEquals("Crescent", app.computeOctavianLunarPhase("April", 1, "Crescent"));
        assertEquals("Gibbous", app.computeOctavianLunarPhase("April", 1, "Crescent"));
    }

    @Test
    @DisplayName("Maximum valid day for 30-day month")
    void testMaxDay30Month() {
        assertNotEquals("Quarter", app.computeOctavianLunarPhase("June", 30, "NewMoon"));
        assertEquals("Full", app.computeOctavianLunarPhase("June", 30, "NewMoon"));
    }

    @Test
    @DisplayName("Maximum valid day for 31-day month")
    void testMaxDay31Month() {
        assertEquals("Gibbous", app.computeOctavianLunarPhase("July", 31, "NewMoon"));
    }

    @Test
    @DisplayName("Phase exactly after full cycles")
    void testExactFullCycle() {
        // 8 * 4 = 32 days — should be same phase
        assertEquals("Full", app.computeOctavianLunarPhase("February", 4, "Quarter"));
        assertNotEquals("Quarter", app.computeOctavianLunarPhase("February", 4, "Quarter"));
    }

}