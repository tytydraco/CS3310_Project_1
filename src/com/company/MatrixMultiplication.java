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

    private int[][] _naive(int startRow, int endRow, int startCol, int endCol) {
        int size = endRow - startRow;
        int half = size / 2;

        int[][] c = new int[size][size];

        System.out.println(size);
        if (size == 2) {
            c[0][0] = a[startRow][startCol] * b[startRow][startCol] + a[startRow][endCol] * b[endRow][startCol];    // TOP LEFT
            c[0][1] = a[startRow][startCol] * b[startRow][endCol] + a[startRow][endCol] * b[endRow][endCol];        // TOP RIGHT
            c[1][0] = a[endRow][startCol] * b[startRow][startCol] + a[endRow][endCol] * b[endRow][startCol];        // BOT LEFT
            c[1][1] = a[endRow][startCol] * b[startRow][endCol] + a[endRow][endCol] * b[endRow][endCol];            // BOT RIGHT
        } else {
            int[][] c11 = _naive(0, half, 0, half);                   // TOP LEFT
            int[][] c12 = _naive(half + 1, size, 0, half);            // TOP RIGHT
            int[][] c21 = _naive(0, half, half + 1, size);            // BOT LEFT
            int[][] c22 = _naive(half + 1, size, half + 1, size);     // BOT RIGHT

            // TODO: combine into big boy
            // TODO: fix looping forever
            // TODO: ughhhhh
        }

        return c;
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
