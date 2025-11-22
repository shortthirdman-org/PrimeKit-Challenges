package com.shortthirdman.primekit.hackerrank;

import com.shortthirdman.primekit.common.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryTreeTest {

    BinaryTree tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree();
    }

    @Test
    void minDepth_SingleLeaf() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, tree.minDepth(root), "Expected min depth for single node to be 1.");
    }

    @Test
    void minDepth_TwoLevels() {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        assertEquals(2, tree.minDepth(root), "Expected min depth for single child tree to be 2.");
    }

    @Test
    void minDepth_ThreeLevels() {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), new TreeNode(4));
        assertEquals(2, tree.minDepth(root), "Expected min depth for balanced tree with three levels to be 2.");
    }

    @Test
    void minDepth_BalancedTree() {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertEquals(2, tree.minDepth(root), "Expected min depth for balanced tree to be 2.");
    }

    // Negative test cases
    @Test
    void minDepth_EmptyTree() {
        TreeNode root = null;
        assertEquals(0, tree.minDepth(root), "Expected min depth for null tree to be 0.");
    }

    // Edge cases
    @Test
    void minDepth_LeftHeavyTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, tree.minDepth(root));
    }

    @Test
    void minDepth_RightHeavyTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, tree.minDepth(root));
    }

    // Parameterized test cases
    static Object[][] provideMinDepthCases() {
        return new Object[][] {
                {null, 0}, // empty tree
                {new TreeNode(1), 1}, // single node
                {new TreeNode(1, new TreeNode(2), null), 2}, // left child only
                {new TreeNode(1, null, new TreeNode(3)), 2}, // right child only
                {new TreeNode(1, new TreeNode(2), new TreeNode(3)), 2}, // balanced tree
                {new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3)), 2} // left heavy
        };
    }

    @ParameterizedTest
    @MethodSource("provideMinDepthCases")
    void minDepth_Parameterized(TreeNode root, int expectedDepth) {
        assertEquals(expectedDepth, tree.minDepth(root));
    }

    // Test for a large balanced tree
    @Test
    void minDepth_LargeBalancedTree() {
        TreeNode root = createBalancedTree(1, 17); // height of 17, so depth should be 17
        assertEquals(17, tree.minDepth(root));
    }

    // Test for a large skewed tree (left-heavy)
    @Test
    void minDepth_LargeLeftHeavyTree() {
        TreeNode root = createLeftHeavyTree(100000); // 100000 nodes deep
        assertEquals(100000, tree.minimumDepth(root));
    }

    // Helper method to create a balanced binary tree
    private TreeNode createBalancedTree(int value, int height) {
        if (height <= 0) return null;
        TreeNode node = new TreeNode(value);
        node.left = createBalancedTree(value, height - 1);
        node.right = createBalancedTree(value, height - 1);
        return node;
    }

    // Helper method to create a left-heavy tree
    private TreeNode createLeftHeavyTree(int nodes) {
        if (nodes <= 0) return null;
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= nodes; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        return root;
    }

    @Test
    public void minDepth_ComplexTree() {
        // Test case for a more complex tree
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4,
                                new TreeNode(7),
                                null),
                        new TreeNode(5)),
                new TreeNode(3));
        assertNotEquals(3, tree.minDepth(root), "Expected min depth for complex tree not to be 3."); // Shortest path to leaf 3
        assertEquals(2, tree.minDepth(root), "Expected min depth for complex tree to be 2.");
    }

    @Test
    public void maxDepth_NullTree() {
        // Test case for an empty tree
        assertEquals(0, tree.maxDepth(null), "Expected max depth for null tree to be 0.");
    }

    @Test
    public void maxDepth_LeafNode() {
        // Test case for a single leaf node
        TreeNode root = new TreeNode(1);
        assertEquals(1, tree.maxDepth(root), "Expected max depth for single node to be 1.");
    }

    @Test
    public void maxDepth_SingleChildTree() {
        // Test case where the tree has one child
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        assertEquals(2, tree.maxDepth(root), "Expected max depth for single child tree to be 2.");
    }

    @Test
    public void maxDepth_BalancedTree() {
        // Test case for a balanced tree
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3));
        assertEquals(3, tree.maxDepth(root), "Expected max depth for balanced tree to be 3.");
    }

    @Test
    public void maxDepth_ComplexTree() {
        // Test case for a more complex tree
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4,
                                new TreeNode(7),
                                null),
                        new TreeNode(5)),
                new TreeNode(3));
        assertEquals(4, tree.maxDepth(root), "Expected max depth for complex tree to be 4.");
    }

    @Test
    public void maxDepth_OnlyLeftChildren() {
        // Test case for a tree with only left children
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                null),
                        null),
                null);
        assertEquals(4, tree.maxDepth(root), "Expected max depth for left-only tree to be 4.");
    }

    @Test
    public void maxDepth_OnlyRightChildren() {
        // Test case for a tree with only right children
        TreeNode root = new TreeNode(1,
                null,
                new TreeNode(2,
                        null,
                        new TreeNode(3,
                                null,
                                new TreeNode(4))));
        assertEquals(4, tree.maxDepth(root), "Expected max depth for right-only tree to be 4.");
    }

    @ParameterizedTest
    @MethodSource("provideTreesForParameterizedTest")
    public void maxDepth_Parameterized(TreeNode root, int expectedDepth) {
        assertEquals(expectedDepth, tree.maxDepth(root), "Expected max depth does not match.");
    }

    private static Object[][] provideTreesForParameterizedTest() {
        return new Object[][] {
                { null, 0 }, // Empty tree
                { new TreeNode(1), 1 }, // Single node
                { new TreeNode(1, new TreeNode(2), null), 2 }, // One left child
                { new TreeNode(1, null, new TreeNode(2)), 2 }, // One right child
                { new TreeNode(1,
                        new TreeNode(2, new TreeNode(3), null),
                        new TreeNode(4)), 3 }, // Mixed tree
                { new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(3,
                                        new TreeNode(4),
                                        null),
                                null),
                        null), 4 } // All left children
        };
    }

    @Test
    void isSymmetric_testSymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        assertTrue(tree.isSymmetric(root), "The tree should be symmetric.");
    }

    @Test
    void isSymmetric_testAsymmetricTreeDifferentValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        assertFalse(tree.isSymmetric(root), "The tree should not be symmetric.");
    }

    @Test
    void isSymmetric_testAsymmetricTreeNulls() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);

        assertFalse(tree.isSymmetric(root), "The tree should not be symmetric.");
    }

    @Test
    void isSymmetric_testSingleNodeTree() {
        TreeNode root = new TreeNode(1);

        assertTrue(tree.isSymmetric(root), "A single node tree should be symmetric.");
    }

    @Test
    void isSymmetric_testTwoLevelTreeSymmetric() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        assertTrue(tree.isSymmetric(root), "The two-level tree should be symmetric.");
    }

    @Test
    void isSymmetric_testTwoLevelTreeAsymmetric() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        assertFalse(tree.isSymmetric(root), "The two-level tree should not be symmetric.");
    }

    @Test
    void isSymmetric_testEmptyTree() {
        assertTrue(tree.isSymmetric(null), "An empty tree should be symmetric.");
    }

    @Test
    void isSymmetric_testLargeSymmetricTree() {
        TreeNode root = generateBalancedSymmetricTree(1, 4); // Height 4
        assertTrue(tree.isSymmetric(root), "A large balanced tree should be symmetric.");
    }

    @Test
    void isSymmetric_testLargeAsymmetricTree() {
        TreeNode root = generateBalancedAsymmetricTree(1, 4); // Height 4
        assertFalse(tree.isSymmetric(root), "A large unbalanced tree should not be symmetric.");
    }

    // Helper method to create a balanced symmetric tree
    private TreeNode generateBalancedSymmetricTree(int value, int height) {
        if (height <= 0) {
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = generateBalancedSymmetricTree(value + 1, height - 1);
        node.right = generateBalancedSymmetricTree(value + 1, height - 1);
        return node;
    }

    // Helper method to create a balanced asymmetric tree
    private TreeNode generateBalancedAsymmetricTree(int value, int height) {
        if (height <= 0) {
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = generateBalancedAsymmetricTree(value + 1, height - 1);
        node.right = new TreeNode(value + 1, generateBalancedAsymmetricTree(value + 1, height - 1), null);
        return node;
    }

    @Test
    void isSymmetric_testTreeWithNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(-1);
        root.left.left = new TreeNode(-2);
        root.left.right = new TreeNode(-3);
        root.right.left = new TreeNode(-3);
        root.right.right = new TreeNode(-2);

        assertTrue(tree.isSymmetric(root), "The tree with negative values should be symmetric.");
    }

    @Test
    void isSymmetric_testTreeWithNegativeAndPositiveValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-2);
        root.left.left = new TreeNode(-3);
        root.left.right = new TreeNode(-4);
        root.right.left = new TreeNode(-4);
        root.right.right = new TreeNode(-3);

        assertTrue(tree.isSymmetric(root), "The tree with mixed values should be symmetric.");
    }

    @Test
    void isBalanced_testBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        assertTrue(tree.isBalanced(root), "The tree should be balanced.");
    }

    @Test
    void isBalanced_testUnbalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        assertFalse(tree.isBalanced(root), "The tree should not be balanced.");
    }

    @Test
    void isBalanced_testAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        assertFalse(tree.isBalanced(root), "The tree should not be balanced.");
    }

    @Test
    void isBalanced_testEmptyTree() {
        assertTrue(tree.isBalanced(null), "An empty tree should be considered balanced.");
    }

    @Test
    void isBalanced_testSingleNodeTree() {
        TreeNode root = new TreeNode(1);
        assertTrue(tree.isBalanced(root), "A single node tree should be balanced.");
    }

    @Test
    void isBalanced_testTwoLevelBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        assertTrue(tree.isBalanced(root), "A two-level tree should be balanced.");
    }

    @Test
    void isBalanced_testTwoLevelAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        assertTrue(tree.isBalanced(root), "The two-level tree should be balanced.");
    }

    @Test
    void isBalanced_testUnbalancedTwoLevelTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);

        assertFalse(tree.isBalanced(root), "The two-level tree should not be balanced.");
    }

    @Test
    void isBalanced_testLargeBalancedTree() {
        TreeNode root = generateBalancedTree(1, 5); // Height 5
        assertTrue(tree.isBalanced(root), "A large balanced tree should be balanced.");
    }

    @Test
    void isBalanced_testLargeAsymmetricTree() {
        TreeNode root = generateAsymmetricTree(1, 5); // Height 5
        assertFalse(tree.isBalanced(root), "A large unbalanced tree should not be balanced.");
    }

    // Helper method to create a balanced tree
    private TreeNode generateBalancedTree(int value, int height) {
        if (height <= 0) {
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = generateBalancedTree(value + 1, height - 1);
        node.right = generateBalancedTree(value + 1, height - 1);
        return node;
    }

    // Helper method to create an asymmetric tree
    private TreeNode generateAsymmetricTree(int value, int height) {
        if (height <= 0) {
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = generateAsymmetricTree(value + 1, height - 1);
        node.right = new TreeNode(value + 1);
        return node;
    }

    @Test
    void isBalanced_testTreeWithNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(-1);
        root.left.left = new TreeNode(-2);
        root.left.right = new TreeNode(-3);
        root.right.left = new TreeNode(-3);
        root.right.right = new TreeNode(-2);

        assertTrue(tree.isBalanced(root), "The tree with negative values should be balanced.");
    }

    @Test
    void isBalanced_testTreeWithNegativeAndPositiveValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-2);
        root.left.left = new TreeNode(-3);
        root.left.right = new TreeNode(-4);
        root.right.left = new TreeNode(-4);
        root.right.right = new TreeNode(-3);

        assertTrue(tree.isBalanced(root), "The tree with mixed values should be balanced.");
    }

    @Test
    void isBalanced_testExample1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(4);

        assertFalse(tree.isBalanced(root), "The tree should not be balanced.");
    }

    @Test
    void isBalanced_testExample2() {
        assertTrue(tree.isBalanced(null), "An empty tree should be considered balanced.");
    }

    @Test
    void isBalanced_testExample3() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        assertTrue(tree.isBalanced(root), "The tree should be balanced.");
    }
}