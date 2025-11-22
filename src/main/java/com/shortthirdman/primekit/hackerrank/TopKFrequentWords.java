package com.shortthirdman.primekit.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>K Most Frequent Words</h3>
 * Given a list of words, return the k most frequent words sorted by frequency and then lexicographically.
 * @author ShortThirdMan
 */
@SuppressWarnings("PMD.UnusedPrivateMethod")
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        // Count frequency for each word.
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Sort words by frequency (higher first) then lexicographically (ascending).
        List<String> candidates = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(candidates, (w1, w2) -> {
            int freqCompare = frequencyMap.get(w2) - frequencyMap.get(w1);
            if (freqCompare == 0) {
                return w1.compareTo(w2);
            }
            return freqCompare;
        });

        // Return the top k frequent words.
        return candidates.subList(0, Math.min(k, candidates.size()));
    }

    /**
     * Optimized top-k frequent collection of word
     * @param words the input array of words
     * @param k the most frequent words value
     * @return
     */
    private List<String> optimizeTopKFrequent(String[] words, int k) {
        if (k <= 0 || words == null || words.length == 0) {
            return Collections.emptyList();
        }

        // Count frequency for each word, including null
        final Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Sort words by frequency (higher first) then lexicographically (null-safe)
        List<String> candidates = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(candidates, (w1, w2) -> {
            int freqCompare = frequencyMap.get(w2) - frequencyMap.get(w1);
            if (freqCompare == 0) {
                // null-safe lexicographic ordering
                if (w1 == null && w2 == null) return 0;
                if (w1 == null) return 1;  // put nulls last
                if (w2 == null) return -1; // put nulls last
                return w1.compareTo(w2);
            }
            return freqCompare;
        });

        // Return top k frequent words
        return candidates.subList(0, Math.min(k, candidates.size()));
    }
}
