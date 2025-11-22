package com.shortthirdman.primekit.codesignal;

/**
 * In a highly automated warehouse, a robot is tasked with organizing packages stored in a rectangular grid.
 * The grid is represented as a 2D list of integers matrix, where each integer represents a package type.
 * To manage the sorting and rearrangement, the robot follows a series of commands given as an array of strings commands.<br/>
 * Each command instructs the robot to perform a specific operation on the matrix:
 * <br/>
 *     "swapRows r1 r2": swaps the elements of row r1 with the elements of row r2.<br/>
 *     "swapColumns c1 c2": swaps the elements of column c1 with the elements of column c2.<br/>
 *     "reverseRow r": reverses the elements of row r.<br/>
 *     "reverseColumn c": reverses the elements of column c.<br/>
 *     "rotate90Clockwise": rotates the entire matrix 90 degrees clockwise.<br/>
 * <br/>
 * Here, r, r1, and r2 are integer row indices, and c, c1, and c2 are integer column indices.
 * @author ShortThirdMan
 */
public class MatrixTransformer {

    /**
     * Transform matrics with given commands
     * @param matrix the input matrix
     * @param commands the commands given to robot
     * @return
     */
    public int[][] transformMatricesWithCommands(int[][] matrix, String[] commands) {
        for (String command : commands) {
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "swapRows":
                    int r1 = Integer.parseInt(parts[1]);
                    int r2 = Integer.parseInt(parts[2]);
                    int[] tempRow = matrix[r1];
                    matrix[r1] = matrix[r2];
                    matrix[r2] = tempRow;
                    break;

                case "swapColumns":
                    int c1 = Integer.parseInt(parts[1]);
                    int c2 = Integer.parseInt(parts[2]);
                    for (int i = 0; i < matrix.length; i++) {
                        int temp = matrix[i][c1];
                        matrix[i][c1] = matrix[i][c2];
                        matrix[i][c2] = temp;
                    }
                    break;

                case "reverseRow":
                    int r = Integer.parseInt(parts[1]);
                    int cols = matrix[r].length;
                    for (int i = 0; i < cols / 2; i++) {
                        int temp = matrix[r][i];
                        matrix[r][i] = matrix[r][cols - 1 - i];
                        matrix[r][cols - 1 - i] = temp;
                    }
                    break;

                case "reverseColumn":
                    int c = Integer.parseInt(parts[1]);
                    int rows = matrix.length;
                    for (int i = 0; i < rows / 2; i++) {
                        int temp = matrix[i][c];
                        matrix[i][c] = matrix[rows - 1 - i][c];
                        matrix[rows - 1 - i][c] = temp;
                    }
                    break;

                case "rotate90Clockwise":
                    matrix = rotate90Clockwise(matrix);
                    break;
            }
        }
        return matrix;
    }

    private  int[][] rotate90Clockwise(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] rotated = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][m - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }
}
