package com.shortthirdman.primekit.codesignal;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays of numbers, firstArray and secondArray. Return the length of the longest common prefix (LCP) between
 * any pair of numbers from different arrays or 0 if no common prefix exists.
 * <br/>
 * A prefix of a number is a number formed by one or more of its digits, starting from its highest-order digit.
 * For example, 123 is a prefix of the number 12345 and 2 is a prefix of the number 234.
 * <br/>
 * A common prefix of two numbers is a number, which is a prefix of both.
 * For instance, longest common prefix (LCP) of 5655359 and 56554 is 5655 and there is no common prefix of 123 and 456.
 *
 * @author ShortThirdMan
 */
public class LowestCommonPrefix {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
    }

    static class Trie {
        TrieNode root = new TrieNode();

        // Insert number as string into Trie
        void insert(String number) {
            TrieNode node = root;
            for (char digit : number.toCharArray()) {
                node = node.children.computeIfAbsent(digit, k -> new TrieNode());
            }
        }

        // Find length of longest prefix between number and Trie contents
        int findLongestPrefix(String number) {
            TrieNode node = root;
            int length = 0;
            for (char digit : number.toCharArray()) {
                if (!node.children.containsKey(digit)) {
                    break;
                }
                node = node.children.get(digit);
                length++;
            }
            return length;
        }
    }

    /**
     * Brute-force approach
     * @param firstArray the first input array of integers
     * @param secondArray the second input array of integers
     * @return the longest common prefix
     */
    public int findLCP(int[] firstArray, int[] secondArray) {
        if (firstArray.length == 0 || secondArray.length == 0) {
            return 0;
        }

        int maxLength = 0;

        for (int num1 : firstArray) {
            String str1 = String.valueOf(num1);
            for (int num2 : secondArray) {
                String str2 = String.valueOf(num2);
                int commonLength = commonPrefixLength(str1, str2);
                maxLength = Math.max(maxLength, commonLength);
            }
        }

        return maxLength;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    private int commonPrefixLength(String a, String b) {
        int minLength = Math.min(a.length(), b.length());
        int i = 0;
        while (i < minLength && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        return i;
    }

    /**
     * Optimized approach
     * @param firstArray the first input of array of integers
     * @param secondArray the first input of array of integers
     * @return the longest common prefix
     */
    public int findLongestCommonPrefix(int[] firstArray, int[] secondArray) {
        if (firstArray.length == 0 || secondArray.length == 0) {
            return 0;
        }

        // Decide which array to insert into the Trie (smaller one for better performance)
        int[] insertArray = firstArray.length < secondArray.length ? firstArray : secondArray;
        int[] searchArray = firstArray.length < secondArray.length ? secondArray : firstArray;

        Trie trie = new Trie();

        // Insert all numbers (as strings) into Trie
        for (int num : insertArray) {
            trie.insert(String.valueOf(num));
        }

        int maxLength = 0;

        // Search LCP for each number in the other array
        for (int num : searchArray) {
            String strNum = String.valueOf(num);
            int lcpLength = trie.findLongestPrefix(strNum);
            maxLength = Math.max(maxLength, lcpLength);
        }

        return maxLength;
    }
}
