package com.shortthirdman.primekit.hackerrank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesExpressionTest {

    ValidParenthesesExpression app;

    @BeforeEach
    void setUp() {
        app = new ValidParenthesesExpression();
    }

    static Stream<TestCase> provideValidBraceTestCases() {
        return Stream.of(
                // --- Positive cases ---
                new TestCase("{}", true), // simple valid pair
                new TestCase("{{}}", true), // nested valid
                new TestCase("{*}", true), // * as empty
                new TestCase("{**}", true), // * as empty or {}
                new TestCase("{*{}}", true), // * as empty or {
                new TestCase("{*{*}}", true), // multiple wildcards
                new TestCase("{***}", true), // * in all forms
                new TestCase("*{}", true), // * as empty
                new TestCase("{*}*", true), // * after match
                new TestCase("*", true), // * as empty
                new TestCase("**", true), // multiple * as empty
                new TestCase("{*{**}*}", true), // deep nesting with *

                // --- Negative cases ---
                new TestCase("}", false), // closing first
                new TestCase("{{}", false), // missing close
                new TestCase("{}}", false), // extra closing
                new TestCase("}*", false), // starts with invalid close
                new TestCase("{{**", true), // unbalanced even with *

                // --- Edge cases ---
                new TestCase("", true), // empty string is valid
                new TestCase("****", true), // all stars
                new TestCase("{", false), // single open
                new TestCase("*{", false), // unmatched open at end
                new TestCase("{**}}", true), // stars as closing
                new TestCase("{*}}", true) // not enough stars to close
        );
    }

    @ParameterizedTest(name = "Test case #{index} - input='{0}'")
    @MethodSource("provideValidBraceTestCases")
    @DisplayName("Parameterized test with input '{0}'")
    void testIsValidParameterized(TestCase testCase) {
        boolean result = app.isValid(testCase.input());
        assertEquals(testCase.expected(), result);
    }

    @Test
    void testNullStringThrowsException() {
        assertThrows(NullPointerException.class, () -> app.isValid(null));
    }

    // Helper record
    private record TestCase(String input, boolean expected) {}
}