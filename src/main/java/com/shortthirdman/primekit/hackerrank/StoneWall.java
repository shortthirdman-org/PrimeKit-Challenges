package com.shortthirdman.primekit.hackerrank;

/**
 * You are going to build a stone wall. The wall should be straight and <code>N</code> meters long, and its thickness should be constant;
 * however, it should have different heights in different places. The height of the wall is specified by an array <code>H</code> of N positive integers.
 * <code>H[i]</code> is the height of the wall from <code>i to i+1</code> meters to the right of its left end.
 * <br>
 * In particular, <code>H[0]</code> is the height of the wall's left end and <code>H[N−1]</code> is the height of the wall's right end.
 * The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular).
 *
 * @author ShortThirdMan
 */
public class StoneWall {

    /**
     * Compute the minimum number of blocks needed to build the wall.
     * @param H array of positive integers specifying height of wall
     * @return Minimum number of blocks needed to build
     */
    public int coverManhattanSkyline(int[] H) {
        int N = H.length;
        int totalBlocks = 0;

        if (N < 1 || N > 100000) {
            throw new IllegalArgumentException("Number of height-blocks should be in range [1,100000]");
        }

        for (int height : H) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height values must be positive");
            }
            totalBlocks += height;
        }

        return totalBlocks;
    }
}
