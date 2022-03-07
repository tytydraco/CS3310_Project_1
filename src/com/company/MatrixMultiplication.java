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

        System.out.println(Arrays.deepHashCode(result));
        return result;
    }

    private int[][] _naive(int[][] a, int[][] b) {
        int[][] c = new int[a.length][a.length];

        if (a.length == 1) {
            c[0][0] = a[0][0] * b[0][0];
            return c;
        } else {
            int[][] c11 = matrixAdd(
                    _naive(partitionMatrix(a, 1, 1), partitionMatrix(b, 1, 1)),
                    _naive(partitionMatrix(a, 1, 2),  partitionMatrix(b, 2, 1)));
            int[][] c12 = matrixAdd(
                    _naive(partitionMatrix(a, 1, 1), partitionMatrix(b, 1, 2)),
                    _naive(partitionMatrix(a, 1, 2),  partitionMatrix(b, 2, 2)));
            int[][] c21 = matrixAdd(
                    _naive(partitionMatrix(a, 2, 1), partitionMatrix(b, 1, 1)),
                    _naive(partitionMatrix(a, 2, 2),  partitionMatrix(b, 2, 1)));
            int[][] c22 = matrixAdd(
                    _naive(partitionMatrix(a, 2, 1), partitionMatrix(b, 1, 2)),
                    _naive(partitionMatrix(a, 2, 2),  partitionMatrix(b, 2, 2)));

            return combinePartitions(c11, c12, c21, c22, c);
        }
    }

    /**
     * Multiply two matrices by divide-and-conquer
     *
     * O(n^3)
     */
    public int[][] naive() {
        int[][] c = _naive(a,b);
        System.out.println(Arrays.deepHashCode(c));
        return c;
    }

    private int[][] matrixAdd(int[][] a, int[][] b) {
        int[][] c = new int[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }

        return c;
    }

    private int[][] matrixSub(int[][] a, int[][] b) {
        int[][] c = new int[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }

        return c;
    }

    private int[][] combinePartitions(int[][] c11, int[][] c12, int[][] c21, int[][] c22, int[][] c) {
        int length = c11.length;

        for (int i = 0; i < c11.length; i++) {
            for (int j = 0; j < c11.length; j++) {
                c[i][j] = c11[i][j];
                c[i][j+length] = c12[i][j];
                c[i+length][j] = c21[i][j];
                c[i+length][j+length] = c22[i][j];
            }
        }

        return c;
    }

    private int[][] partitionMatrix(int[][] A, int row, int col) {
        int length = A.length / 2;
        int[][] partitionA = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (row == 1 & col == 1)
                    partitionA[i][j] = A[i][j];
                if (row == 1 & col == 2)
                    partitionA[i][j] = A[i][j+length];
                if (row == 2 & col == 1)
                    partitionA[i][j] = A[i+length][j];
                if (row == 2 & col == 2)
                    partitionA[i][j] = A[i+length][j+length];
            }
        }

        return partitionA;
    }

    /**
     * Multiply two matrices using Strassen's method
     *
     * O(n^2.8)
     */
    public int[][] strassens() {
        int[][] c = _strassens(a, b);
        System.out.println(Arrays.deepHashCode(c));
        return c;
    }

    private int[][] _strassens(int[][] a, int[][] b) {
        int[][] c = new int[a.length][a.length];
    
        if (a.length == 1) {
            c[0][0] = a[0][0] * b[0][0];
            return c;
        }
        else {
            int[][] A11 = partitionMatrix(a, 1, 1);
            int[][] A12 = partitionMatrix(a, 1, 2);
            int[][] A21 = partitionMatrix(a, 2, 1);
            int[][] A22 = partitionMatrix(a, 2, 2);

            int[][] B11 = partitionMatrix(b, 1, 1);
            int[][] B12 = partitionMatrix(b, 1, 2);
            int[][] B21 = partitionMatrix(b, 2, 1);
            int[][] B22 = partitionMatrix(b, 2, 2);

            int[][] P1 = _strassens(matrixAdd(A11, A22), matrixAdd(B11, B22));
            int[][] P2 = _strassens(matrixAdd(A21, A22), B11);
            int[][] P3 = _strassens(A11, matrixSub(B12, B22));
            int[][] P4 = _strassens(A22, matrixSub(B21, B11));
            int[][] P5 = _strassens(matrixAdd(A11, A12), B22);
            int[][] P6 = _strassens(matrixSub(A21, A11), matrixAdd(B11, B12));
            int[][] P7 = _strassens(matrixSub(A12, A22), matrixAdd(B21, B22));
            int[][] C11 = matrixAdd(matrixSub(matrixAdd(P1, P4), P5), P7);
            int[][] C12 = matrixAdd(P3, P5);
            int[][] C21 = matrixAdd(P2, P4);
            int[][] C22 = matrixAdd(matrixAdd(matrixSub(P1, P2), P3), P6);

            return combinePartitions(C11, C12, C21, C22, c);
        }
    }
}
