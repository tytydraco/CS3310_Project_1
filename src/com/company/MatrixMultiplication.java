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
    public int[][] traditional(int[][] a, int[][] b) {  
        // I added the 2 parameters bc i thought i would use it for the naive... turns out i didnt need
        //we can remove later if u want 
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

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");

        return result;
    }

    /*private int[][] combineIntoBigBoy(int[][] c11, int[][] c12, int[][] c21, int[][] c22) {
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
    }*/

    private int[][] _naive(int rowA, int colA, int rowB, int colB, int[][] c, int length) {
        int size = rowA;
        int half = rowA / 2;

        //int[][] c = new int[size][size];
        //System.out.println(size);
        if (length == 2) {
            //we subtract 1 bc index base 0, same logic w/ subtract 2
            c[rowA-2][colA-2] = a[rowA-2][colA-2] * b[rowB-2][colB-2] + a[rowA-2][colA-1] * b[rowB-1][colB-2];
            c[rowA-2][colA-1] = a[rowA-2][colA-2] * b[rowB-2][colB-1] + a[rowA-2][colA-1] * b[rowB-1][colB-1];
            c[rowA-1][colA-2] = a[rowA-1][colA-2] * b[rowB-2][colB-2] + a[rowA-1][colA-1] * b[rowB-1][colB-2];
            c[rowA-1][colA-1] = a[rowA-1][colA-2] * b[rowB-2][colB-1] + a[rowA-1][colA-1] * b[rowB-1][colB-1];

            for (int i = 0; i < c.length; i++) {
                for (int j = 0; j < c.length; j++) {
                    System.out.print(c[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n");
            return c;

        } else {
            int[][] c11 = _naive(half, half, half, half, c, length/2);                   // TOP LEFT
            int[][] c12 = _naive(half, size, half, size, c, length/2);            // TOP RIGHT
            int[][] c21 = _naive(size, half, size, half, c, length/2);            // BOT LEFT
            int[][] c22 = _naive(size, size, size, size, c, length/2);     // BOT RIGHT

            for (int i = 0; i < c.length; i++) {
                for (int j = 0; j < c.length; j++) {
                    System.out.print(c[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n");

            return c;
        }
    }

    /**
     * Multiply two matrices by divide-and-conquer
     *
     * O(n^3)
     */
    public int[][] naive() {
        int[][] result = new int[a.length][a.length];
        return _naive(a.length, a.length, a.length, a.length, result, a.length);
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
