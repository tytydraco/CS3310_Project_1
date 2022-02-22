package com.company;

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

    /**
     * Multiply two matrices by divide-and-conquer
     *
     * O(n^3)
     */
    public int[][] naive() {
        return new int[][] {};
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
