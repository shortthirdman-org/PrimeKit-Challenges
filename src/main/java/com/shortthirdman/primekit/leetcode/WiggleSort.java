package com.shortthirdman.primekit.leetcode;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] &lt; nums[1] &gt; nums[2] &lt; nums[3]....
 * @author shortthirdman
 */
public class WiggleSort {

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i - 1, i);
                }
            } else {
                if (nums[i - 1] < nums[i]) {
                    swap(nums, i - 1, i);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
