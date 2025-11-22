package com.shortthirdman.primekit.hackerrank;

@SuppressWarnings("PMD.UselessParentheses")
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

        // Determine the winner using ternary operator
        return ericaScore > bobScore ? "Erica" : (bobScore > ericaScore ? "Bob" : "Tie");
    }
}
