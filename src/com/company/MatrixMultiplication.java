package com.company;

import java.util.Arrays;

public class MatrixMultiplication {
    int[][] a;
    int[][] b;

    public MatrixMultiplication(int[][] a, int[][] b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Find dot product of a certain row and column for two matrices
     *
     * O(n)
     */
    private int rowDotCol(int row, int col) {
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
    public int[][] traditional() {
        /*
         * Typically, we multiply each ROW of `a` by each COLUMN of `b` (dot product)
         */

        int n = a.length;
        int[][] result = new int[n][n];

        for (int aRowIdx = 0; aRowIdx < n; aRowIdx++) {
            for (int bColIdx = 0; bColIdx < n; bColIdx++) {
                int dotProduct = rowDotCol(aRowIdx, bColIdx);
                result[aRowIdx][bColIdx] = dotProduct;
            }
        }

        return result;
    }

    private int[][] combineIntoBigBoy(int[][] c11, int[][] c12, int[][] c21, int[][] c22) {
        int size = c11.length * 2;
        int half = c11.length;

        int[][] c = new int[size][size];

        for (int col = 0; col < half; col++) {
            System.arraycopy(c11[col], 0, c[col], 0, half);
            System.arraycopy(c12[col], 0, c[col], half + 1, half);
        }

        for (int col = half + 1; col < size; col++) {
            System.arraycopy(c21[col], 0, c[col], 0, half);
            System.arraycopy(c22[col], 0, c[col], half + 1, half);
        }

        return c;
    }

    private int[][] _naive(int rowA, int colA, int rowB, int colB) {
        int size = colA - rowA;
        int half = size / 2;

        int[][] c = new int[size][size];

        if (size == 1) {
            c[0][0] = a[rowA][rowB] * b[rowB][colB];
            return c;
        } else {
            int[][] c11 = _naive(0, half, 0, half);                   // TOP LEFT
            int[][] c12 = _naive(half + 1, size, 0, half);            // TOP RIGHT
            int[][] c21 = _naive(0, half, half + 1, size);            // BOT LEFT
            int[][] c22 = _naive(half + 1, size, half + 1, size);     // BOT RIGHT

            int[][] g = combineIntoBigBoy(c11, c12, c21, c22);
            System.out.println(Arrays.deepToString(g));

            // TODO: combine into big boy
            // TODO: fix looping forever
            // TODO: ughhhhh

            return g;
        }
    }

    /**
     * Multiply two matrices by divide-and-conquer
     *
     * O(n^3)
     */
    public int[][] naive() {
        return _naive(0, a.length, 0, a.length);
    }

    /**
     * Multiply two matrices using Strassen's method
     *
     * O(n^2.8)
     */
    public int[][] strassens() {
        return new int[][] {};
    }
}
