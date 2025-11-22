package com.shortthirdman.primekit.hackerearth;

import com.shortthirdman.primekit.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Operations using the {@link ListNode}
 * @author ShortThirdMan
 * @since 1.1.0
 */
public class ListNodeOps {

    /**
     * Merges an array of sorted linked lists into a single sorted linked list.
     *
     * <p>The algorithm uses a priority queue (min-heap) to efficiently extract the
     * smallest element among the current heads of all lists. This ensures that the
     * resulting merged list remains sorted.</p>
     *
     * <p>Time Complexity: <code>O(N log k)</code>, where <code>N</code> is the total number of nodes across
     * all lists and <code>k</code> is the number of linked lists.</p>
     *
     * <p>Space Complexity: <code>O(k)</code>, for storing the current heads of the lists in the priority queue.</p>
     *
     * @param lists an array of {@link ListNode} objects representing the heads of sorted linked lists
     * @return the head of the merged sorted linked list, or {@code null} if the input is empty
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap based on node values
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.value));

        // Add initial nodes of all lists
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    /**
     * Swaps every two adjacent nodes in the linked list.
     *
     * <p>The algorithm uses an iterative approach with a dummy node to
     * simplify pointer manipulation. At each step, it swaps the two
     * adjacent nodes and moves forward.</p>
     *
     * <p>Time Complexity: O(n), where n is the number of nodes in the list.</p>
     * <p>Space Complexity: O(1), as the swaps are performed in place.</p>
     *
     * @param head the head of the input linked list
     * @return the new head after swapping pairs
     */
    public ListNode swapPairs(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            if (current.value < 0 || current.value > 100) {
                throw new IllegalArgumentException("Node values must be between 0 and 100. Found: " + current.value);
            }
            if (count > 100) {
                throw new IllegalArgumentException("Number of nodes exceeds the allowed limit of 100.");
            }
            current = current.next;
        }

        // Iterative swapping
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // Swap
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move prev forward
            prev = first;
        }

        return dummy.next;
    }

    /**
     * Reverses nodes in k-sized groups in a linked list.
     *
     * <p>Constraints enforced:
     * <ul>
     *     <li>1 &le; k &le; n, where <code>n</code> is the number of nodes in the list</li>
     *     <li>0 &le; node.val &le; 1000</li>
     * </ul>
     * </p>
     *
     * <p>Time Complexity: <code>O(n)</code>, where <code>n</code> is the number of nodes.</p>
     * <p>Space Complexity: <code>O(1)</code>, as the reversal is done in-place.</p>
     *
     * @param head the head of the input linked list
     * @param k the size of groups to reverse
     * @return the head of the modified linked list after reversing k-groups
     * @throws IllegalArgumentException if constraints are violated
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 0) throw new IllegalArgumentException("k must be positive.");

        // Constraint check
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            if (cur.value < 0 || cur.value > 1000)
                throw new IllegalArgumentException("Node values must be 0-1000. Found: " + cur.value);
            cur = cur.next;
        }
        if (k > count)
            throw new IllegalArgumentException("k cannot be greater than the number of nodes.");

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // Find kth node safely using a simple for-loop
            ListNode kth = groupPrev;
            for (int i = 0; i < k; i++) {
                if (kth.next == null) {
                    kth = null;
                    break;
                }
                kth = kth.next;
            }
            if (kth == null) break;

            ListNode groupNext = kth.next;

            // Reverse the group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            ListNode tmp = groupPrev.next; // new tail
            groupPrev.next = kth;           // new head
            groupPrev = tmp;                // move prev to tail
        }

        return dummy.next;
    }
}
