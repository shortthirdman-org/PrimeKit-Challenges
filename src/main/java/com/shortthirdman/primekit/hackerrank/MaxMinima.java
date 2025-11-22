package com.shortthirdman.primekit.hackerrank;

import java.util.List;

public class MaxMinima {

//    public int maxMin(List<Integer> arr, int k) {
//        if (k < 1 || k > arr.size()) {
//            throw new IllegalArgumentException("k should be between 1 and input array size");
//        }
//
//        boolean elementGrTh1 = arr.stream().anyMatch(e -> e == 0);
//        if (elementGrTh1) {
//            throw new IllegalArgumentException("Element should be between 1 and 10^9");
//        }
//
//        // Deque will store indices, and maintain elements in increasing order
//        Deque<Integer> dq = new ArrayDeque<>();
//        int maxOfMins = Integer.MIN_VALUE;
//
//        for (int i = 0; i < arr.size(); i++) {
//
//            // 1️⃣ Remove elements out of the current window
//            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
//                dq.pollFirst();
//            }
//
//            // 2️⃣ Maintain monotonicity: remove bigger elements from the back
//            while (!dq.isEmpty() && arr.get(dq.peekLast()) >= arr.get(i)) {
//                dq.pollLast();
//            }
//
//            // 3️⃣ Add current index
//            dq.offerLast(i);
//
//            // 4️⃣ Window becomes valid only when i >= k-1
//            if (i >= k - 1 && !dq.isEmpty()) {
//                int currentMin = arr.get(dq.peekFirst());
//                maxOfMins = Math.max(maxOfMins, currentMin);
//            }
//        }
//
//        return maxOfMins;
//    }

    public int maxMin(List<Integer> arr, int k) {
        int n = arr.size();
        int log = (int) (Math.log(n) / Math.log(2));

        if (k < 1 || k > n) {
            throw new IllegalArgumentException("k should be between 1 and input array size");
        }

        boolean elementGrTh1 = arr.stream().anyMatch(e -> e == 0);
        if (elementGrTh1) {
            throw new IllegalArgumentException("Element should be between 1 and 10^9");
        }

        // Sparse Table
        int[][] st = new int[n][log + 1];

        // Initialize level 0 with the original array
        for (int i = 0; i < n; i++) {
            st[i][0] = arr.get(i);
        }

        // Build Sparse Table
        for (int j = 1; j <= log; j++) {
            int len = 1 << j;
            int half = len >> 1;
            for (int i = 0; i + len <= n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + half][j - 1]);
            }
        }

        // Query each window of size k
        int maxOfMins = Integer.MIN_VALUE;
        int p = (int) (Math.log(k) / Math.log(2));
        int powLen = 1 << p;

        for (int i = 0; i + k <= n; i++) {
            int left = st[i][p];
            int right = st[i + k - powLen][p];
            int windowMin = Math.min(left, right);

            maxOfMins = Math.max(maxOfMins, windowMin);
        }

        return maxOfMins;
    }
}
