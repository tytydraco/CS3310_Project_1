package com.company;

import java.util.Arrays;

public class Main {
    public static final int[][] sanityA = {
            {2, 0, -1, 6},
            {3, 7, 8, 0},
            {-5, 1, 6, -2},
            {8, 0, 2, 7}
    };

    public static final int[][] sanityB = {
            {0, 1, 6, 3},
            {-2, 8, 7, 1},
            {2, 0, -1, 0},
            {9, 1, 6, -2}
    };

    public static final int[][] aTimesB = {
            {52, 8, 49, -6},
            {2, 59, 59, 16},
            {-8, 1, -41, -10},
            {67, 15, 88, 10}
    };

    public static void main(String[] args) {
        int[][] traditionalResult = traditional(sanityA, sanityB);
        System.out.println(Arrays.deepToString(traditionalResult));
    }

    /**
     * Find dot product of a certain row and column for two matrices
     *
     * O(n)
     */
    private static int rowDotCol(int[][] a, int[][] b, int row, int col) {
        int n = a.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            int aValue = a[row][i];
            int bValue = b[i][col];
            result += aValue * bValue;
        }

        return result;
    }

    /**
     * Multiply two matrices by taking the dot product of each row and column
     *
     * O(n^3)
     */
    private static int[][] traditional(int[][] a, int[][] b) {
        /*
         * Typically, we multiply each ROW of `a` by each COLUMN of `b` (dot product)
         */

        int n = a.length;
        int[][] result = new int[n][n];

        for (int aRowIdx = 0; aRowIdx < n; aRowIdx++) {
            for (int bColIdx = 0; bColIdx < n; bColIdx++) {
                int dotProduct = rowDotCol(a, b, aRowIdx, bColIdx);
                result[aRowIdx][bColIdx] = dotProduct;
            }
        }

        return result;
    }

    /**
     * Multiply two matrices by divide-and-conquer
     *
     * O(n^3)
     */
    private static int[][] naive(int[][] a, int[][] b) {
        return new int[][] {};
    }

    /**
     * Multiply two matrices using Strassen's method
     *
     * O(n^2.8)
     */
    private static int[][] strassens(int[][] a, int[][] b) {
        return new int[][] {};
    }
}
