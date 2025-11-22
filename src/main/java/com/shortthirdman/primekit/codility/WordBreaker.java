package com.shortthirdman.primekit.codility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be broken into one or more dictionary words.
 * Note that the same word in the dictionary may be reused
 * multiple times in the segmentation.
 *
 * <pre>
 *     Example 1:
 *     Input: s = "microsoft", wordDict = ["micro","soft"]
 *     Output: true
 *
 *     Example 2:
 *     Input: s = "appletenapple", wordDict = ["apple","ten"]
 *     Output: true
 *     Note that you are allowed to reuse a dictionary word.
 *
 *     Example 3:
 *     Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 *     Output: false
 * </pre>
 * Constraints -
 * <pre>
 *     1 &le; s.length &le; 300
 *     1 &le; wordDict.length &le; 1000
 *     1 &le; wordDictfil length &le; 20
 * </pre>
 * @author ShortThirdMan
 */
public class WordBreaker {

    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert the list to a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);

        // Create a DP array where dp[i] means whether s.substring(0, i) can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // An empty string can always be segmented

        // Iterate over each position in the string
        for (int i = 1; i <= s.length(); i++) {
            // Check every possible start position for the current end position i
            for (int j = 0; j < i; j++) {
                // Check only if the prefix up to j can be segmented and
                // the substring from j to i is in the dictionary
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check further, we've found a valid segmentation
                }
            }
        }

        // The answer is whether the entire string can be segmented
        return dp[s.length()];
    }
}
