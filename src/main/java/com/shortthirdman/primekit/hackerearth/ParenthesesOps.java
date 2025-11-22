package com.shortthirdman.primekit.hackerearth;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Utility class providing algorithms for solving common parentheses-related problems.
 *
 * @author ShortThirdMan
 * @since 1.0.1
 */
public class ParenthesesOps {

    /**
     * Returns the length of the longest valid (well-formed) parentheses substring
     * within the given string. A valid substring consists of properly matched
     * opening '(' and closing ')' parentheses.
     *
     * <p>Examples:
     * <ul>
     *   <li>Input: "(()" → Output: 2 ("()")</li>
     *   <li>Input: ")()())" → Output: 4 ("()()")</li>
     *   <li>Input: "" → Output: 0</li>
     * </ul>
     *
     * <p>The algorithm runs in O(n) time using a stack to track indices of unmatched
     * parentheses. Space complexity is O(n) in the worst case.
     *
     * @param s the input string containing only characters '(' and ')'.
     * @return the length of the longest valid parentheses substring. Returns 0 if
     *         the input is null, empty, or contains no valid substring.
     * @throws IllegalArgumentException if the input length exceeds 3 * 10^4 or if
     *         the string contains characters other than '(' or ')'.
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0; // constraint check
        }

        if (s.length() > 30000) {
            throw new IllegalArgumentException("Input exceeds max length of 3 * 10^4");
        }

        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '(':
                    stack.push(i);
                    break;

                case ')':
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        maxLen = Math.max(maxLen, i - stack.peek());
                    }
                    break;

                default:
                    // If constraints are trusted, this should never occur.
                    throw new IllegalArgumentException("Invalid character: " + c);
            }
        }

        return maxLen;
    }

    /**
     * Generates all combinations of well-formed parentheses for a given number of pairs.
     *
     * <p>A well-formed parentheses string must satisfy:
     * <ul>
     *   <li>Every opening '(' has a corresponding closing ')'.</li>
     *   <li>No prefix of the string has more ')' than '('.</li>
     * </ul>
     *
     * <p>Examples:
     * <ul>
     *   <li>Input: n = 3 → Output: ["((()))","(()())","(())()","()(())","()()()"]</li>
     *   <li>Input: n = 1 → Output: ["()"]</li>
     * </ul>
     *
     * @param n the number of pairs of parentheses (1 ≤ n ≤ 8).
     * @return a list of all well-formed parentheses combinations.
     * @throws IllegalArgumentException if n is outside the range [1, 8].
     */
    public List<String> generateParenthesis(int n) {
        if (n < 1 || n > 8) {
            throw new IllegalArgumentException("n must be between 1 and 8");
        }

        List<String> result = new ArrayList<>();
        // Define a recursive lambda (Java 8 trick: self-reference via array wrapper)
        BiConsumer<StringBuilder, int[]> dfs = new BiConsumer<>() {
            @Override
            public void accept(StringBuilder current, int[] state) {
                int open = state[0], close = state[1];

                if (current.length() == n * 2) {
                    result.add(current.toString());
                    return;
                }

                if (open < n) {
                    current.append('(');
                    accept(current, new int[]{open + 1, close});
                    current.deleteCharAt(current.length() - 1);
                }
                if (close < open) {
                    current.append(')');
                    accept(current, new int[]{open, close + 1});
                    current.deleteCharAt(current.length() - 1);
                }
            }
        };

        dfs.accept(new StringBuilder(), new int[]{0, 0});

        return result;
    }
}
