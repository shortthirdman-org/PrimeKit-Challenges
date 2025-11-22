package com.shortthirdman.primekit.codesignal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTransformerTest {

    MatrixTransformer app;

    @BeforeEach
    void setUp() {
        app = new MatrixTransformer();
    }

    // Positive Test: Sample Input Provided
    @Test
    public void testSampleInput() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        String[] commands = {
                "swapRows 0 2",
                "swapColumns 1 2",
                "reverseRow 0",
                "reverseColumn 2",
                "rotate90Clockwise"
        };
        int[][] expected = {
                {1, 4, 8},
                {3, 6, 9},
                {7, 5, 2}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "The sample input should produce the expected output.");
    }

    // Positive Test: Swap Rows
    @Test
    public void testSwapRows() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        String[] commands = {"swapRows 0 2"};
        int[][] expected = {
                {7, 8, 9},
                {4, 5, 6},
                {1, 2, 3}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "Row swap did not perform as expected.");
    }

    // Positive Test: Swap Columns
    @Test
    public void testSwapColumns() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        String[] commands = {"swapColumns 0 2"};
        int[][] expected = {
                {3, 2, 1},
                {6, 5, 4},
                {9, 8, 7}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "Column swap did not perform as expected.");
    }

    // Positive Test: Reverse Row
    @Test
    public void testReverseRow() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        String[] commands = {"reverseRow 1"};
        int[][] expected = {
                {1, 2, 3},
                {6, 5, 4}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "Row reversal did not perform as expected.");
    }

    // Positive Test: Reverse Column
    @Test
    public void testReverseColumn() {
        int[][] matrix = {
                {1,  2, 3},
                {4,  5, 6},
                {7,  8, 9}
        };
        String[] commands = {"reverseColumn 1"};
        // In column 1, the values [2,5,8] become [8,5,2]
        int[][] expected = {
                {1, 8, 3},
                {4, 5, 6},
                {7, 2, 9}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "Column reversal did not perform as expected.");
    }

    // Positive Test: Rotate 90 Degrees Clockwise on a non-square matrix
    @Test
    public void testRotate90Clockwise() {
        int[][] matrix = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        String[] commands = {"rotate90Clockwise"};
        int[][] expected = {
                {5, 3, 1},
                {6, 4, 2}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "Matrix did not rotate 90 degrees clockwise as expected.");
    }

    // Edge Test: When no commands are provided the matrix remains unchanged.
    @Test
    public void testEmptyCommands() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        String[] commands = {};  // No operations
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "Matrix should remain unchanged when no commands are executed.");
    }

    // Edge Test: Single element matrix (1x1)
    @Test
    public void testEdgeCaseSingleElementMatrix() {
        int[][] matrix = {
                {42}
        };
        String[] commands = {
                "swapRows 0 0",
                "swapColumns 0 0",
                "reverseRow 0",
                "reverseColumn 0",
                "rotate90Clockwise"
        };
        int[][] expected = {
                {42}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "A 1x1 matrix should remain unchanged regardless of the operations.");
    }

    // Negative Test: Invalid Command (unknown operation should be ignored)
    @Test
    public void testInvalidCommand() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        String[] commands = {"invalidCommand"};
        // Since our implementation ignores unrecognized commands, the output should be unchanged.
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] result = app.transformMatricesWithCommands(matrix, commands);
        assertArrayEquals(expected, result, "An invalid command should result in no changes to the matrix.");
    }

    // Negative Test: Out-of-bound indices for swapRows
    @Test
    public void testOutOfBoundsIndicesSwapRows() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        String[] commands = {"swapRows 0 2"};
        // Expect an ArrayIndexOutOfBoundsException because row index 2 is invalid.
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> app.transformMatricesWithCommands(matrix, commands), "swapRows with an out-of-bound index should throw an exception.");
    }

    // Negative Test: Out-of-bound indices for swapColumns
    @Test
    public void testOutOfBoundsIndicesSwapColumns() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        String[] commands = {"swapColumns 0 3"};
        // Expect an ArrayIndexOutOfBoundsException because column index 3 is invalid.
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> app.transformMatricesWithCommands(matrix, commands), "swapColumns with an out-of-bound index should throw an exception.");
    }

    // Negative Test: Invalid number format in command arguments
    @Test
    public void testInvalidNumberFormat() {
        int[][] matrix = {
                {1, 2, 3}
        };
        String[] commands = {"swapRows a 0"};
        // Expect a NumberFormatException due to the invalid row index.
        assertThrows(NumberFormatException.class, () -> app.transformMatricesWithCommands(matrix, commands), "A command with an invalid number format should throw a NumberFormatException.");
    }
}