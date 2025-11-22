package com.shortthirdman.primekit.hackerrank;

/**
 * There are n people standing in a line labeled from <code>1 to n</code>. The first person in the line is holding a pillow initially.
 * Every second, the person holding the pillow passes it to the next person standing in the line. Once the pillow reaches the end of the line,
 * the direction changes, and people continue passing the pillow in the opposite direction.
 * <br></br>
 * For example, once the pillow reaches the <code>nth</code> person they pass it to the <code>n - 1th</code> person,
 * then to the <code>n - 2th</code> person and so on.
 * Given the two positive integers <code>n</code> and <code>time</code>, return the <b>index</b> of the person holding the pillow
 * after <b>time</b> seconds.
 *
 * @author ShortThirdMan
 * @see <a href="https://leetcode.com/problems/pass-the-pillow/description/">LeetCode</a>
 */
public class PassThePillow {

    public int pillowPass(int n, int time) {
        if (n < 2 || n > 1000 || time < 1 || time > 1000) {
            throw new IllegalArgumentException("Invalid input for n or time");
        }

        int cycleTime = 2 * (n - 1);
        int effectiveTime = time % cycleTime;

        if (effectiveTime <= n - 1) {
            return 1 + effectiveTime;
        } else {
            return n - (effectiveTime - (n - 1));
        }
    }
}
