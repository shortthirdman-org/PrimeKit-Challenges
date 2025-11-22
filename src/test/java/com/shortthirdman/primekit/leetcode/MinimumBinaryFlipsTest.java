package com.shortthirdman.primekit.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinimumBinaryFlipsTest {

	MinimumBinaryFlips app;
	
	@BeforeEach
	public void setUp() {
		app = new MinimumBinaryFlips();
	}
	
	@Test
	public void testGetMinFlips_defaultScenario() {
		String firstPwd = "100110";
        String secondPwd = "101011";
        String thirdPwd = "1110011000";
        
        assertEquals(3, app.getMinFlips(firstPwd));
        assertEquals(2, app.getMinFlips(secondPwd));
        assertEquals(3, app.getMinFlips(thirdPwd));
	}

	// Positive Test Cases
	@Test
	void testAllZeros() {
		assertEquals(0, app.getMinFlips("0000"));
	}

	@Test
	void testAllOnes() {
		assertEquals(0, app.getMinFlips("1111"));
	}

	@Test
	void testAlternatingCharacters() {
		assertEquals(3, app.getMinFlips("010101"));
		assertNotEquals(2, app.getMinFlips("010101"));
	}

	@Test
	void testMixedCharacters() {
		assertEquals(0, app.getMinFlips("1100"));
		assertNotEquals(1, app.getMinFlips("1100"));
	}

	@Test
	void testShortString() {
		assertEquals(0, app.getMinFlips("1")); // Edge case with a single character
	}

	@Test
	void testTwoDifferentChars() {
		assertEquals(1, app.getMinFlips("01"));
	}

	// Negative Test Cases
	@Test
	void testEmptyString() {
		assertEquals(0, app.getMinFlips(""));
	}

	@Test
	void testSingleCharacterZero() {
		assertEquals(0, app.getMinFlips("0"));
	}

	@Test
	void testSingleCharacterOne() {
		assertEquals(0, app.getMinFlips("1"));
	}

	@Test
	void testTwoSameChars() {
		assertEquals(0, app.getMinFlips("00")); // Both are zeros
		assertEquals(0, app.getMinFlips("11")); // Both are ones
	}

	// Edge Cases
	@Test
	void testLargeInput() {
		StringBuilder largeInput = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			largeInput.append(i % 2 == 0 ? '0' : '1');
		}
		assertEquals(5000, app.getMinFlips(largeInput.toString()));
	}

	@Test
	void testLongSameChars() {
		String longZeros = "0".repeat(10000);
		assertEquals(0, app.getMinFlips(longZeros));

		String longOnes = "1".repeat(10000);
		assertEquals(0, app.getMinFlips(longOnes));
	}

	// Exception Handling Test Cases
	@Test
	void testNullInput() {
		Exception exception = assertThrows(NullPointerException.class, () -> app.getMinFlips(null));
		assertEquals("Input text password can not be null", exception.getMessage());
	}
}
