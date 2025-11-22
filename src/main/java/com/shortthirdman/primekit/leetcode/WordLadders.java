package com.shortthirdman.primekit.leetcode;

import com.shortthirdman.primekit.common.LinkedWordNode;
import com.shortthirdman.primekit.common.WordNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordLadders {

    /**
     * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end,
     * such that only one letter can be changed at a time and each intermediate word must exist in the dictionary.
     * @param beginWord the word to begin with
     * @param endWord the word to end with
     * @param wordList the list of words
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        HashSet<String> unvisited = new HashSet<>(wordList);

        LinkedList<LinkedWordNode> queue = new LinkedList<>();
        LinkedWordNode node = new LinkedWordNode(beginWord, 0, null);
        queue.offer(node);

        int minLen = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            LinkedWordNode top = queue.poll();

            //top if have shorter result already
            if (!result.isEmpty() && top.getDepth() > minLen) {
                return result;
            }

            for (int i = 0; i < top.getWord().length(); i++) {
                char c = top.getWord().charAt(i);
                char[] arr = top.getWord().toCharArray();
                for (char j = 'z'; j >= 'a'; j--) {
                    if (j == c) {
                        continue;
                    }
                    arr[i] = j;
                    String t = new String(arr);

                    if (t.equals(endWord)) {
                        //add to result
                        List<String> aResult = new ArrayList<>();
                        aResult.add(endWord);
                        LinkedWordNode p = top;
                        while (p != null) {
                            aResult.add(p.getWord());
                            p = p.getPrev();
                        }

                        Collections.reverse(aResult);
                        result.add(aResult);

                        //stop if get shorter result
                        if (top.getDepth() <= minLen) {
                            minLen = top.getDepth();
                        } else {
                            return result;
                        }
                    }

                    if (unvisited.contains(t)) {
                        LinkedWordNode n = new LinkedWordNode(t, top.getDepth() + 1, top);
                        queue.offer(n);
                        unvisited.remove(t);
                    }
                }
            }
        }

        return result;
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        wordDict.add(endWord);

        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.getWord();

            if (word.equals(endWord)) {
                return top.getNumSteps();
            }

            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }

                    String newWord = new String(arr);
                    if (wordDict.contains(newWord)) {
                        queue.add(new WordNode(newWord, top.getNumSteps() + 1));
                        wordDict.remove(newWord);
                    }

                    arr[i] = temp;
                }
            }
        }

        return 0;
    }
}
