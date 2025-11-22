package com.shortthirdman.primekit.hackerearth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParenthesesOpsTest {

    ParenthesesOps app;

    @BeforeEach
    void setUp() {
        app = new ParenthesesOps();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void longestValidParentheses() {
        // Example cases from the problem statement
        assertEquals(2, app.longestValidParentheses("(()"), "Expected 2 for input '(()'");
        assertEquals(4, app.longestValidParentheses(")()())"), "Expected 4 for input ')()())'");
        assertEquals(0, app.longestValidParentheses(""), "Expected 0 for empty input");

        // Edge cases
        assertEquals(0, app.longestValidParentheses("("), "Single '(' should return 0");
        assertEquals(0, app.longestValidParentheses(")"), "Single ')' should return 0");
        assertEquals(2, app.longestValidParentheses("()"), "Expected 2 for input '()'");
        assertEquals(0, app.longestValidParentheses(")("), "Expected 0 for input ')('");

        // Multiple valid substrings
        assertEquals(6, app.longestValidParentheses("()(())"), "Expected 6 for input '()(())'");
        assertEquals(4, app.longestValidParentheses("()()"), "Expected 4 for input '()()'");

        // Complex nesting
        assertEquals(6, app.longestValidParentheses("((()))"), "Expected 6 for input '((()))'");
        assertEquals(8, app.longestValidParentheses("(()(()))"), "Expected 8 for input '(()(()))'");

        // Random mixed patterns
        assertNotEquals(2, app.longestValidParentheses("(()))"), "Expected 4 for input '(()))'");
        assertEquals(4, app.longestValidParentheses("(()))"), "Expected 4 for input '(()))'");
        assertNotEquals(6, app.longestValidParentheses(")()())()(()"), "Expected 6 for input ')()())()(()'");

        // Stress test: very long balanced string (10k '(' followed by 10k ')')
        String longBalanced = "(".repeat(10000) + ")".repeat(10000);
        assertEquals(20000, app.longestValidParentheses(longBalanced),
                "Expected full length 20000 for long balanced parentheses");

        // Stress test: no valid pairs
        String onlyLeft = "(".repeat(30000);
        assertEquals(0, app.longestValidParentheses(onlyLeft), "All '(' should return 0");

        String onlyRight = ")".repeat(30000);
        assertEquals(0, app.longestValidParentheses(onlyRight), "All ')' should return 0");
    }

    @Test
    void generateParenthesis() {
        // Example cases
        assertEquals(List.of("()"), app.generateParenthesis(1), "n=1 should return ['()']");

        List<String> expectedN3 = List.of("((()))","(()())","(())()","()(())","()()()");
        assertTrue(app.generateParenthesis(3).containsAll(expectedN3)
                        && expectedN3.containsAll(app.generateParenthesis(3)),
                "n=3 should return all 5 well-formed combinations");

        // Edge cases
        assertEquals(2, app.generateParenthesis(2).size(),
                "n=2 should generate exactly 2 combinations: (()) and ()()");
        assertThrows(IllegalArgumentException.class, () -> app.generateParenthesis(0),
                "n=0 should throw exception");
        assertThrows(IllegalArgumentException.class, () -> app.generateParenthesis(9),
                "n=9 should throw exception (exceeds constraint)");

        // Upper bound stress case (n=8) - just check size, not content
        // Catalan number C8 = 1430
        assertEquals(1430, app.generateParenthesis(8).size(),
                "n=8 should generate 1430 valid combinations");
    }
}