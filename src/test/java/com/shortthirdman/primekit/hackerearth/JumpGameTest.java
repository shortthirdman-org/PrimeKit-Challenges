package com.shortthirdman.primekit.hackerearth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpGameTest {

    JumpGame game;

    @BeforeEach
    void setUp() {
        game = new JumpGame();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void jump() {
        // ✅ Provided examples
        assertEquals(2, game.jump(new int[]{2, 3, 1, 1, 4}),
                "Should need 2 jumps");
        assertEquals(2, game.jump(new int[]{2, 3, 0, 1, 4}),
                "Should need 2 jumps");

        // ✅ Edge cases
        assertEquals(0, game.jump(new int[]{0}),
                "Single element means no jumps needed");
        assertEquals(1, game.jump(new int[]{1, 0}),
                "Direct jump to last index");
        assertEquals(1, game.jump(new int[]{5, 0, 0, 0, 0}),
                "Big jump covers everything in one go");
        assertEquals(3, game.jump(new int[]{1, 1, 1, 1}),
                "Must jump each step to move forward");

        // ❌ Constraint validation
        assertThrows(IllegalArgumentException.class,
                () -> game.jump(null),
                "Null input should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.jump(new int[0]),
                "Empty array should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.jump(new int[10_001]),
                "Array longer than 10^4 should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.jump(new int[]{-1, 2, 3}),
                "Negative values should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.jump(new int[]{1001}),
                "Values greater than 1000 should throw exception");

        // 🛠 More edge validations
        assertEquals(2, game.jump(new int[]{3, 2, 1, 0, 4}),
                "Greedy should still find the optimal 2 jumps");
        assertEquals(2, game.jump(new int[]{4, 1, 1, 3, 1, 1, 1}),
                "First jump to index 3, second jump to last");
    }

    @Test
    void canJump() {
        // ✅ Positive cases
        assertTrue(game.canJump(new int[]{2, 3, 1, 1, 4}),
                "Should be able to reach the last index");
        assertTrue(game.canJump(new int[]{1}),
                "Single element array should always return true");
        assertTrue(game.canJump(new int[]{5, 0, 0, 0, 0}),
                "Large jump at the start should allow reaching the end");

        // ❌ Negative cases
        assertFalse(game.canJump(new int[]{3, 2, 1, 0, 4}),
                "Should not be able to reach the last index");
        assertFalse(game.canJump(new int[]{0, 2, 3}),
                "Cannot move past index 0 with zero jump length");
        assertFalse(game.canJump(new int[]{1, 0, 1, 0}),
                "Should get stuck at the second zero");

        // 🛠 Edge cases
        assertTrue(game.canJump(new int[]{0}),
                "Array with single zero is trivially at the end");
        assertTrue(game.canJump(new int[]{2, 5, 0, 0}),
                "Should be able to leap over trailing zeros");
        assertFalse(game.canJump(new int[]{1, 1, 0, 0}),
                "Should get stuck before last index");
        assertTrue(game.canJump(new int[]{9, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
                "Very large jump should instantly reach the end");
        assertFalse(game.canJump(new int[]{1, 2, 1, 0, 0, 0}),
                "Trailing zeros without enough reach should fail");
        assertTrue(game.canJump(new int[]{1, 2, 3, 4, 0, 0, 1}),
                "Last element itself is reachable despite intermediate zeros");

        // 🛑 Constraint validation
        assertThrows(IllegalArgumentException.class,
                () -> game.canJump(null),
                "Null array should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.canJump(new int[0]),
                "Empty array should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.canJump(new int[10_001]),
                "Array longer than 10^4 should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.canJump(new int[]{-1, 2, 3}),
                "Negative values should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> game.canJump(new int[]{100_001}),
                "Values greater than 10^5 should throw exception");
    }
}