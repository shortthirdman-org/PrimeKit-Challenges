package com.shortthirdman.primekit.hackerrank;

import java.util.*;
import java.util.function.Predicate;

public class AutoCorrectAnagram {

    private static final Predicate<String> LOWERCASE_ONLY =
            s -> s != null && s.matches("[a-z]*");

    public List<List<String>> getSearchResults(List<String> words, List<String> queries) {

        validateList(words, "words");
        validateList(queries, "queries");

        // Map: sorted letters → all words that share that pattern
        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String w : words) {
            String key = sortChars(w);
            anagramMap.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
        }

        // Alphabetically sort each anagram list once
        for (List<String> group : anagramMap.values()) {
            Collections.sort(group);
        }

        List<List<String>> results = new ArrayList<>();

        for (String q : queries) {
            String key = sortChars(q);
            results.add(anagramMap.getOrDefault(key, Collections.emptyList()));
        }

        return results;
    }

    private String sortChars(String s) {
        return s.chars()
                .sorted()
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    private void validateList(List<String> list, String label) {
        if (list == null) {
            throw new IllegalArgumentException(label + " list cannot be null.");
        }

        // Check for null elements
        if (list.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException(label + " list contains null elements.");
        }

        // Check for lowercase-only rule
        if (!list.stream().allMatch(LOWERCASE_ONLY)) {
            String badValue = list.stream()
                    .filter(s -> !LOWERCASE_ONLY.test(s))
                    .findFirst()
                    .orElse("?");
            throw new IllegalArgumentException(
                    label + " list contains invalid characters: " + badValue
            );
        }
    }
}
