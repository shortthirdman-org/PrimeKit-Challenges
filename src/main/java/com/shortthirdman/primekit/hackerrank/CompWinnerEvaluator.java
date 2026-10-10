package com.shortthirdman.primekit.hackerrank;

/**
 * This class evaluates the winner of a competition between Erica and Bob based on their scores.
 * Each character in their respective strings contributes to their total score:
 * <pre>
 *  - 'E' contributes 1 point
 *  - 'M' contributes 3 points
 *  - 'H' contributes 5 points
 * </pre>
 * The winner is determined by comparing their total scores, and in case of a tie, it returns "Tie".
 * @author ShortThirdMan
 * @since 1.1.0
 * @see <a href="https://www.hackerrank.com/challenges/comp-winner">Competition Winner</a>
 */
public class CompWinnerEvaluator {

    public String evaluateWinner(String erica, String bob) {
        int ericaScore = 0;
        int bobScore = 0;

        // Calculate Erica's score
        for (char c : erica.toCharArray()) {
            switch (c) {
                case 'E':
                    ericaScore += 1;
                    break;
                case 'M':
                    ericaScore += 3;
                    break;
                case 'H':
                    ericaScore += 5;
                    break;
            }
        }

        // Calculate Bob's score
        for (char b : bob.toCharArray()) {
            switch (b) {
                case 'E':
                    bobScore += 1;
                    break;
                case 'M':
                    bobScore += 3;
                    break;
                case 'H':
                    bobScore += 5;
                    break;
            }
        }

        // Determine the winner using a ternary operator
        return ericaScore > bobScore ? "Erica" : bobScore > ericaScore ? "Bob" : "Tie";
    }
}
