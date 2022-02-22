package com.company;

public class Main {

    final int[][] sanityA = {
            {2, 0, -1, 6},
            {3, 7, 8, 0},
            {-5, 1, 6, -2},
            {8, 0, 2, 7}
    };

    final int[][] sanityB = {
            {0, 1, 6, 3},
            {-2, 8, 7, 1},
            {2, 0, -1, 0},
            {9, 1, 6, -2}
    };

    public static void main(String[] args) {
        
    }

    private static int[][] traditional(int[][] a, int[][] b) {
        /*
         * Typically, we multiply each ROW of `a` by each COLUMN of `b` (dot product)
         */

        int[][] result = new int[][] {};

        for (int aRowIdx = 0; aRowIdx < a.length; aRowIdx++) {
            for (int bColIdx = 0; bColIdx < b.length; bColIdx++) {

            }
        }

        return new int[][] {};
    }

    private static int[][] naive(int[][] a, int[][] b) {
        return new int[][] {};
    }

    private static int[][] strassens(int[][] a, int[][] b) {
        return new int[][] {};
    }
}
