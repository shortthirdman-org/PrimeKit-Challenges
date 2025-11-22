package com.shortthirdman.primekit.hackerrank;

import java.util.Arrays;

/**
 * There are <code>n</code> students in a class numbered from <code>0</code> to <code>n - 1</code>.
 * The teacher will give each student a problem starting with the student number <code>0</code>, then the student number <code>1</code>, and so on
 * until the teacher reaches the student number <code>n - 1</code>. After that, the teacher will restart the process,
 * starting with the student number <code>0</code> again.<br><br/>
 *
 * You are given a <b>0-indexed</b> integer array chalk and an integer <code>k</code>.
 * There are initially <code>k</code> pieces of chalk. When the student number <code>i</code> is given a problem to solve,
 * they will use <code>chalk[i]</code> pieces of chalk to solve that problem.
 * However, if the current number of chalk pieces is strictly less than <code>chalk[i]</code>, then the student number <code>i</code>
 * will be asked to replace the chalk. <br><br/>
 *
 * Return the <b>index</b> of the student that will replace the chalk pieces.
 * @author ShortThirdMan
 * @see <a href="https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk/">LeetCode</a>
 */
public class StudentChalkReplacer {

    public int chalkReplacer(int[] chalk, int k) {
        if (chalk == null) {
            throw new NullPointerException("Chalk can not be null");
        }

        if (chalk.length == 0) {
            throw new IllegalArgumentException("Number of chalks cannot be zero");
        }

        boolean allPositive = Arrays.stream(chalk).allMatch(n -> n > 0);
        if (!allPositive) {
            throw new IllegalArgumentException("Values for chalk can not be negative");
        }

        if (k < 0) {
            throw new IllegalArgumentException("k can not be negative");
        }

        // Calculate the total chalk needed for one round
        int totalChalk = 0;
        for (int i = 0; i < chalk.length; i++) {
            totalChalk += chalk[i];
        }

        // Reduce k using the modulo operation
        k %= totalChalk;

        // Find the student who will replace the chalk
        for (int i = 0; i < chalk.length; i++) {
            if (k < chalk[i]) {
                return i;
            }
            k -= chalk[i];
        }

        // Should never reach here if input constraints are respected
        return -1;
    }
}
