package com.shortthirdman.primekit.codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Given a string <code>s</code> and a dictionary of strings <code>wordDict</code>, add spaces in s to construct a sentence
 * where each word is a valid dictionary word. Return all such possible sentences in any order.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <br/>
 * <b>Example 1</b>
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * <br/>

 * <b>Example 2</b>
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * <br/>

 * <b>Example 3</b>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []

 * @apiNote <a href="https://leetcode.com/problems/word-break-ii/">Word Break II</a>

 * @author ShortThirdMan
 */
public class WordBreak {

    public List<String> wordBreak(String s, List<String> wordDict) {
        // Convert the word dictionary to a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        // Memoization map
        Map<Integer, List<String>> memo = new HashMap<>();
        return backtrack(s, 0, wordSet, memo);
    }

    private List<String> backtrack(String s, int start, Set<String> wordSet, Map<Integer, List<String>> memo) {
        // If we reach the end of the string
        if (start == s.length()) {
            return Collections.singletonList("");
        }

        // Check if the result is already in the memo
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> sentences = new ArrayList<>();

        // Try every possible end index
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word)) {
                // Recursively break the rest of the string
                List<String> restSentences = backtrack(s, end, wordSet, memo);
                for (String sentence : restSentences) {
                    if (sentence.isEmpty()) {
                        sentences.add(word);
                    } else {
                        sentences.add(word + " " + sentence);
                    }
                }
            }
        }

        // Memorize the result for this start index
        memo.put(start, sentences);
        return sentences;
    }
}
