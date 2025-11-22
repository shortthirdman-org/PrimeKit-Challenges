package com.shortthirdman.primekit.hackerearth;

import com.shortthirdman.primekit.common.ListNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeOpsTest {

    ListNodeOps operations;

    @BeforeEach
    void setUp() {
        operations = new ListNodeOps();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mergeKLists() {
        // Example 1
        ListNode[] lists1 = new ListNode[] {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6)
        };
        ListNode result1 = operations.mergeKLists(lists1);
        assertEquals("1->1->2->3->4->4->5->6", result1.toString());

        // Example 2: Empty input
        ListNode[] lists2 = new ListNode[] {};
        ListNode result2 = operations.mergeKLists(lists2);
        assertNull(result2);

        // Example 3: Single empty list
        ListNode[] lists3 = new ListNode[] { null };
        ListNode result3 = operations.mergeKLists(lists3);
        assertNull(result3);

        // Single non-empty list
        ListNode[] lists4 = new ListNode[] { ListNode.of(1, 2, 3) };
        ListNode result4 = operations.mergeKLists(lists4);
        assertEquals("1->2->3", result4.toString());

        // Multiple empty lists
        ListNode[] lists5 = new ListNode[] { null, null, null };
        ListNode result5 = operations.mergeKLists(lists5);
        assertNull(result5);

        // With negative numbers
        ListNode[] lists6 = new ListNode[] {
                ListNode.of(-10, -5, 0),
                ListNode.of(-6, -3, 2),
                ListNode.of(-8, -4, 1)
        };
        ListNode result6 = operations.mergeKLists(lists6);
        assertEquals("-10->-8->-6->-5->-4->-3->0->1->2", result6.toString());
    }

    @Test
    void swapPairs() {
        // Example 1
        ListNode head1 = ListNode.of(1, 2, 3, 4);
        ListNode result1 = operations.swapPairs(head1);
        assertEquals("2->1->4->3", result1.toString());

        // Example 2: Empty list
        ListNode head2 = null;
        ListNode result2 = operations.swapPairs(head2);
        assertNull(result2);

        // Example 3: Single node
        ListNode head3 = ListNode.of(1);
        ListNode result3 = operations.swapPairs(head3);
        assertEquals("1", result3.toString());

        // Example 4: Three nodes
        ListNode head4 = ListNode.of(1, 2, 3);
        ListNode result4 = operations.swapPairs(head4);
        assertEquals("2->1->3", result4.toString());

        // Extra test: Five nodes
        ListNode head5 = ListNode.of(1, 2, 3, 4, 5);
        ListNode result5 = operations.swapPairs(head5);
        assertEquals("2->1->4->3->5", result5.toString());

        // Constraint violation: value out of range
        ListNode invalidValue = ListNode.of(1, 200);
        assertThrows(IllegalArgumentException.class, () -> operations.swapPairs(invalidValue));

        // Constraint violation: too many nodes
        ListNode tooLong = new ListNode(0);
        ListNode current = tooLong;
        for (int i = 1; i <= 101; i++) {
            current.next = new ListNode(i % 101);
            current = current.next;
        }
        assertThrows(IllegalArgumentException.class, () -> operations.swapPairs(tooLong));
    }

    @Test
    void reverseKGroup() {
        // Example 1: k=2
        ListNode head1 = ListNode.of(1, 2, 3, 4, 5);
        ListNode result1 = operations.reverseKGroup(head1, 2);
        assertEquals("2->1->4->3->5", result1.toString());

        // Example 2: k=3
        ListNode head2 = ListNode.of(1, 2, 3, 4, 5);
        ListNode result2 = operations.reverseKGroup(head2, 3);
        assertEquals("3->2->1->4->5", result2.toString());

        // Single node, k=1
        ListNode head3 = ListNode.of(1);
        ListNode result3 = operations.reverseKGroup(head3, 1);
        assertEquals("1", result3.toString());

        // All nodes reversed, k=n
        ListNode head4 = ListNode.of(1, 2, 3);
        assertEquals("3->2->1", operations.reverseKGroup(head4, 3).toString());

        // k greater than number of nodes -> IllegalArgumentException
        ListNode head5 = ListNode.of(1, 2);
        assertThrows(IllegalArgumentException.class, () -> operations.reverseKGroup(head5, 3));

        // Node value constraint violation
        ListNode head6 = ListNode.of(1, 2000);
        assertThrows(IllegalArgumentException.class, () -> operations.reverseKGroup(head6, 2));
    }
}