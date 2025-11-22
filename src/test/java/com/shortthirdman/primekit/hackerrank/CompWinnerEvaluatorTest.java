package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompWinnerEvaluatorTest {

    CompWinnerEvaluator app;

    @BeforeEach
    void setUp() {
        app = new CompWinnerEvaluator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testEricaWins() {
        // Erica has higher score
        String erica = "EHH";
        String bob = "EME";
        assertEquals("Erica", app.evaluateWinner(erica, bob));
    }

    @Test
    public void testBobWins() {
        // Bob has higher score
        String erica = "EM";
        String bob = "HH";
        assertEquals("Bob", app.evaluateWinner(erica, bob));
    }

    @Test
    public void testTie() {
        // Scores are tied
        String erica = "EMH";
        String bob = "HEM";
        assertEquals("Tie", app.evaluateWinner(erica, bob));
    }

    @Test
    public void testEmptyInputs() {
        // Both inputs are empty
        String erica = "";
        String bob = "";
        assertEquals("Tie", app.evaluateWinner(erica, bob));
    }

    @Test
    public void testDifferentLengths() {
        // Different input lengths
        String erica = "EM";
        String bob = "EMM";
        assertEquals("Bob", app.evaluateWinner(erica, bob));
    }

    @Test
    public void testAllEasy() {
        // Both solve only easy problems
        String erica = "EEEEE";
        String bob = "EEE";
        assertEquals("Erica", app.evaluateWinner(erica, bob));
    }

    @Test
    public void testAllHard() {
        // Both solve only hard problems
        String erica = "H";
        String bob = "HH";
        assertEquals("Bob", app.evaluateWinner(erica, bob));
    }
}