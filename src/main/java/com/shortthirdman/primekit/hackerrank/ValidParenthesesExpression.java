package com.shortthirdman.primekit.hackerrank;

/**
 * Given a string that contains only three characters: <code>{</code>, <code>}</code>, and <code>*</code>.
 * A <code>*</code> can represent either a <code>{</code>, a <code>}</code>, or an empty string.
 * @author ShortThirdMan
 */
public class ValidParenthesesExpression {

    public boolean isValid(String s) {
        if (s == null) {
            throw new NullPointerException("Input string can not be null");
        }
        int minOpen = 0; // minimum possible open braces
        int maxOpen = 0; // maximum possible open braces

        for (char c : s.toCharArray()) {
            switch (c) {
                case '{':
                    minOpen++;
                    maxOpen++;
                    break;
                case '}':
                    minOpen--;
                    maxOpen--;
                    break;
                case '*':
                    minOpen--; // treat as '}'
                    maxOpen++; // treat as '{'
                    break;
                default:
                    break;
            }

            // If maxOpen becomes negative, too many closing braces
            if (maxOpen < 0) return false;

            // minOpen can't be negative (we can use * as empty string)
            if (minOpen < 0) minOpen = 0;
        }

        // Valid if we can have 0 open braces at the end
        return minOpen <= 0;
    }
}
