package com.shortthirdman.primekit.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spam Text Classifier:
 *   A text is "spam" if it contains ≥ 2 occurrences of words from spamWords (case-insensitive, exact matches). Otherwise, return "not_spam".
 * @author ShortThirdMan
 * @since 1.1.2
 */
public class SpamTextClassifier {

    /**
     * Classifies a list of texts as "SPAM" or "NOT SPAM" based on the presence of spam words.
     * @param texts the array of texts to classify as spam or not spam
     * @param spamWords an array of words that are considered spam indicators
     * @return a list of strings, where each element is "SPAM" if the corresponding text contains any of the spam words, and "NOT SPAM" otherwise
     */
    public List<String> classifyTexts(List<String> texts, List<String> spamWords) {
        if (texts == null || spamWords == null) {
            throw new NullPointerException("texts and spam words cannot be null");
        }

        if (texts.isEmpty()) {
            return List.of();
        }

        Set<String> spamSet = new HashSet<>();
        for (String word : spamWords) {
            spamSet.add(word.toLowerCase());
        }

        List<String> result = new ArrayList<>(texts.size());

        for (String text : texts) {

            int spamCount = 0;

            // Split words by a single space
            String[] words = text.split(" ");

            for (String word : words) {
                if (spamSet.contains(word.toLowerCase())) {
                    spamCount++;

                    // Early exit optimization
                    if (spamCount >= 2) {
                        result.add("spam");
                        break;
                    }
                }
            }

            if (spamCount < 2) {
                result.add("not_spam");
            }
        }

        return result;
    }

    /**
     * Classifies a list of texts as "SPAM" or "NOT SPAM" based on the presence of spam words.
     * @param texts the list of texts to classify
     * @param spamWords the list of spam words to check against
     * @return a list of classifications ("spam" or "not_spam") for each text in the input list,
     * processed in parallel for improved performance
     */
    public List<String> classifyTextStream(List<String> texts, List<String> spamWords) {
        if (texts == null || spamWords == null) {
            throw new NullPointerException("texts and spam words cannot be null");
        }

        if (texts.isEmpty()) {
            return List.of();
        }

        Set<String> spamSet = spamWords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        return texts.parallelStream()
                .map(text -> {
                    long spamCount = Arrays.stream(text.split(" "))
                            .map(String::toLowerCase)
                            .filter(spamSet::contains)
                            .limit(2)
                            .count();

                    return spamCount >= 2 ? "spam" : "not_spam";
                })
                .toList();
    }
}
