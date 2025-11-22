package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class ReversePolishNotationTest {

    ReversePolishNotation app;

    @BeforeEach
    void setUp() {
        app = new ReversePolishNotation();
    }

    // Positive test cases
    @Test
    void testAddition() {
        String[] tokens = {"2", "1", "+"};
        assertEquals(3, app.evaluateRPN(tokens));
    }

    @Test
    void testSubtraction() {
        String[] tokens = {"5", "3", "-"};
        assertEquals(2, app.evaluateRPN(tokens));
    }

    @Test
    void testMultiplication() {
        String[] tokens = {"4", "2", "*"};
        assertEquals(8, app.evaluateRPN(tokens));
    }

    @Test
    void testDivision() {
        String[] tokens = {"6", "3", "/"};
        assertEquals(2, app.evaluateRPN(tokens));
    }

    @Test
    void testComplexExpression() {
        String[] tokens = {"2", "3", "+", "4", "*"};
        assertEquals(20, app.evaluateRPN(tokens)); // (2 + 3) * 4
    }

    @Test
    void testMultipleOperators() {
        String[] tokens = {"5", "1", "2", "+", "4", "*", "+"};
        assertNotEquals(14, app.evaluateRPN(tokens)); // 5 + (1 + 2) * 4
        assertEquals(17, app.evaluateRPN(tokens));
    }

    // Negative test cases
    @Test
    void testInvalidOperator() {
        String[] tokens = {"2", "3", "@"}; // Invalid operator
        assertThrows(IllegalArgumentException.class, () -> app.evaluateRPN(tokens));
    }

    @Test
    void testInsufficientOperands() {
        String[] tokens = {"1", "+"}; // Not enough operands for operator
        assertThrows(EmptyStackException.class, () -> app.evaluateRPN(tokens));
    }

    @Test
    void testDivisionByZero() {
        String[] tokens = {"1", "0", "/"};
        assertThrows(ArithmeticException.class, () -> app.evaluateRPN(tokens));
    }

    // Edge cases
    @Test
    void testSingleNumber() {
        String[] tokens = {"42"};
        assertEquals(42, app.evaluateRPN(tokens)); // Single operand
    }

    @Test
    void testMultipleSameOperators() {
        String[] tokens = {"5", "5", "+", "5", "-"};
        assertEquals(5, app.evaluateRPN(tokens)); // (5 + 5) - 5
    }

    @Test
    void testZeroDivision() {
        String[] tokens = {"4", "0", "/"};
        assertThrows(ArithmeticException.class, () -> app.evaluateRPN(tokens)); // Division by zero
    }

    @Test
    void testNegativeResult() {
        String[] tokens = {"3", "5", "-"};
        assertEquals(-2, app.evaluateRPN(tokens)); // 3 - 5
    }

    @Test
    void testComplexNegativeResult() {
        String[] tokens = {"4", "2", "*", "10", "-"};
        assertEquals(-2, app.evaluateRPN(tokens)); // (4 * 2) - 10
    }

    // Positive test cases
    @Test
    void shouldReturnSumForAddition() {
        String[] tokens = {"2", "1", "+"};
        assertEquals(3, app.computeRPN(tokens));
    }

    @Test
    void shouldReturnDifferenceForSubtraction() {
        String[] tokens = {"5", "3", "-"};
        assertEquals(2, app.computeRPN(tokens));
    }

    @Test
    void shouldReturnProductForMultiplication() {
        String[] tokens = {"4", "2", "*"};
        assertEquals(8, app.computeRPN(tokens));
    }

    @Test
    void shouldReturnQuotientForDivision() {
        String[] tokens = {"6", "3", "/"};
        assertEquals(2, app.computeRPN(tokens));
    }

    @Test
    void shouldEvaluateComplexExpressionCorrectly() {
        String[] tokens = {"2", "3", "+", "4", "*"};
        assertEquals(20, app.computeRPN(tokens)); // (2 + 3) * 4
    }

    @Test
    void shouldHandleMultipleOperatorsCorrectly() {
        String[] tokens = {"5", "1", "2", "+", "4", "*", "+"};
        assertEquals(17, app.computeRPN(tokens)); // 5 + (1 + 2) * 4
        assertNotEquals(14, app.computeRPN(tokens)); // 5 + (1 + 2) * 4
    }

    // Negative test cases
    @Test
    void shouldThrowExceptionForInvalidOperator() {
        String[] tokens = {"2", "3", "@"}; // Invalid operator
        assertThrows(IllegalArgumentException.class, () -> app.computeRPN(tokens));
    }

    @Test
    void shouldThrowExceptionForInsufficientOperands() {
        String[] tokens = {"1", "+"}; // Not enough operands for operator
        assertThrows(EmptyStackException.class, () -> app.computeRPN(tokens));
    }

    @Test
    void shouldThrowExceptionForDivisionByZero() {
        String[] tokens = {"1", "0", "/"};
        assertThrows(ArithmeticException.class, () -> app.computeRPN(tokens));
    }

    // Edge cases
    @Test
    void shouldReturnSingleNumber() {
        String[] tokens = {"42"};
        assertEquals(42, app.computeRPN(tokens)); // Single operand
    }

    @Test
    void shouldEvaluateMultipleSameOperators() {
        String[] tokens = {"5", "5", "+", "5", "-"};
        assertEquals(5, app.computeRPN(tokens)); // (5 + 5) - 5
    }

    @Test
    void shouldThrowExceptionForZeroDivision() {
        String[] tokens = {"4", "0", "/"};
        assertThrows(ArithmeticException.class, () -> app.computeRPN(tokens)); // Division by zero
    }

    @Test
    void shouldReturnNegativeResultForSubtraction() {
        String[] tokens = {"3", "5", "-"};
        assertEquals(-2, app.computeRPN(tokens)); // 3 - 5
    }

    @Test
    void shouldEvaluateComplexNegativeResult() {
        String[] tokens = {"4", "2", "*", "10", "-"};
        assertEquals(-2, app.computeRPN(tokens)); // (4 * 2) - 10
    }
}