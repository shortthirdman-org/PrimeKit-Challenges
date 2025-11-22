package com.shortthirdman.primekit.hackerrank;

import com.shortthirdman.primekit.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Binary Tree
 * @author ShortThirdMan
 */
public class BinaryTree {

    /**
     * Given a binary tree, find its minimum depth.
     * <br></br>
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     * A leaf is a node with no children.
     *
     * @param root the node with its leaves
     * @return the minimum depth
     * @see <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">Minimum Depth of Binary Tree</a>
     */
    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0; // Base case: if the tree is empty
        }

        // If the node is a leaf node
        if (root.getLeft() == null && root.getRight() == null) {
            return 1; // Count this node
        }

        // If the left child is null, we only consider the right child
        if (root.getLeft() == null) {
            return 1 + minDepth(root.getRight());
        }

        // If the right child is null, we only consider the left child
        if (root.getRight() == null) {
            return 1 + minDepth(root.getLeft());
        }

        // If both children are present, return the minimum depth of both subtrees
        return 1 + Math.min(minDepth(root.getLeft()), minDepth(root.getRight()));
    }

    /**
     * Given the root of a binary tree, return its maximum depth.
     * <br></br>
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * @param root the node with its leaves
     * @return the maximum depth
     * @see <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">Minimum Depth of Binary Tree</a>
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Calculate depth recursively for left and right subtrees
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Given a binary tree, determine if it is height-balanced.
     *
     * @param root the node with its leaves
     * @return true if height is balanced, false otherwise
     * @see <a href="https://leetcode.com/problems/balanced-binary-tree/description/">Balanced Binary Tree</a>
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true; // An empty tree is balanced
        }

        // Stack to simulate recursion
        Deque<TreeNode> stack = new ArrayDeque<>();
        // Map to store the height of each node
        Map<TreeNode, Integer> heights = new HashMap<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            // If the node has not been processed yet, push its children onto the stack
            if (!heights.containsKey(node)) {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                // Mark the node as processed by adding it to the heights map
                heights.put(node, 0);
            } else {
                // If the node has been processed, calculate its height
                stack.pop(); // Remove the node from the stack

                int leftHeight = heights.getOrDefault(node.left, 0);
                int rightHeight = heights.getOrDefault(node.right, 0);

                // Check if the current node is balanced
                if (Math.abs(leftHeight - rightHeight) > 1) {
                    return false; // Tree is unbalanced
                }

                // Calculate the height of the current node
                int height = Math.max(leftHeight, rightHeight) + 1;
                heights.put(node, height);
            }
        }

        return true; // If all nodes are balanced
    }

    /**
     * @param root the root tree node
     * @return the depth of the tree
     */
    public int minimumDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1; // Start with depth 1 since we start from the root

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // Check for null node (just in case)
                if (node == null) continue;

                // Check if we have reached a leaf node
                if (node.getLeft() == null && node.getRight() == null) {
                    return depth; // Return the current depth when we find the first leaf
                }

                // Add children to the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++; // Increase the depth for the next level
        }

        return depth; // In case of an empty tree, though not reached due to checks
    }

    /**
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     *
     * @param root the node with its leaves
     * @return true if tree is symmetric, otherwise false
     * @see <a href="https://leetcode.com/problems/symmetric-tree/description/">Symmetric Tree</a>
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true; // An empty tree is symmetric
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();

            // Both nodes are null, continue checking
            if (left == null && right == null) {
                continue;
            }

            // If one is null or their values don't match, it's not symmetric
            if (left == null || right == null || left.value != right.value) {
                return false;
            }

            // Push children in the order that ensures symmetry
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true; // If we finish checking without issues, it's symmetric
    }
}
