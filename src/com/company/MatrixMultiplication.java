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

    private int[][] _naive(int[][] a, int[][] b, int[][] c, int size, int minRowA, int maxRowA, int minColA, int maxColA, int minRowB, int maxRowB, int minColB, int maxColB) {
        //int[][] c = new int[a.length][a.length];
        //System.out.println("minrowA: " + minRowA + "maxrowA: " + maxRowA + "mincolA: " + minColA + "maxcolA: " + maxColA + "minrowB: " + minRowB + "maxrowB: " + maxRowB + "mincolB: " + minColB + "maxcolB: " + maxColB );

        if (size == 1) {
            //boolean c11 = (maxRowA < size && maxColA < size && maxRowB < size && maxColB < size) || (maxRowA < size && maxColA >= size && maxRowB >= size && maxColB < size); //11 11 12 21
            //boolean c12 = (maxRowA < size && maxColA < size && maxRowB < size && maxColB >= size) || (maxRowA < size && maxColA >= size && maxRowB >= size && maxColB >= size); //11 12 12 22
            //boolean c21 = (maxRowA < size && maxColA < size && maxRowB < size && maxColB < size) || (maxRowA < size && maxColA >= size && maxRowB >= size && maxColB < size);
            //System.out.println(maxColB);
            if (maxRowA < size && maxColA >= size && maxRowB >= size && maxColB < size) 
                c[maxRowA][maxColB] = (a[maxRowA][maxColA] * b[maxRowB][maxColB]) + c[maxRowA][maxColB];
            else if (maxRowA < size && maxColA >= size && maxRowB >= size && maxColB >= size)
                c[maxRowA][maxColB] = (a[maxRowA][maxColA] * b[maxRowB][maxColB]) + c[maxRowA][maxColB];
            else if (maxRowA >= size && maxColA >= size && maxRowB >= size && maxColB < size)
                c[maxRowA][maxColB] = (a[maxRowA][maxColA] * b[maxRowB][maxColB]) + c[maxRowA][maxColB];
            else if (maxRowA >= size && maxColA >= size && maxRowB >= size && maxColB >= size)
                c[maxRowA][maxColB] = (a[maxRowA][maxColA] * b[maxRowB][maxColB]) + c[maxRowA][maxColB];
            else
                c[maxRowA][maxColB] = a[maxRowA][maxColA] * b[maxRowB][maxColB];  //maxrowA is quadrant11

            return c;
        } else {
            size = size/2;
            c= _naive(a, b, c, size, minRowA, maxRowA-size, minColA, maxColA-size, minRowB, maxRowB-size, minColB, maxColB-size); //11 11
            c= _naive(a, b, c, size, minRowA, maxRowA-size, minColA+size, maxColA, minRowB+size, maxRowB, minColB, maxColB-size); // 12 21
            //c = matrixAdd(c, 1, 1, size);

            c = _naive(a, b, c, size, minRowA, maxRowA-size, minColA, maxColA-size, minRowB, maxRowB-size, minColB+size, maxColB);  //11 12
            c= _naive(a, b, c, size, minRowA, maxRowA-size, minColA+size, maxColA, minRowB+size, maxRowB, minColB+size, maxColB);   //12 22
            //c= matrixAdd(c, 1, 2, size);

            c= _naive(a, b, c, size, minRowA+size, maxRowA, minColA, maxColA-size, minRowB, maxRowB-size, minColB, maxColB-size);  //21 11
            c= _naive(a, b, c, size, minRowA+size, maxRowA, minColA+size, maxColA, minRowB+size, maxRowB, minColB, maxColB-size);   //22 21
            //c= matrixAdd(c, 2, 1, size);

            c= _naive(a, b, c, size, minRowA+size, maxRowA, minColA, maxColA-size, minRowB, maxRowB-size, minColB+size, maxColB);  //21 12
            c= _naive(a, b, c, size, minRowA+size, maxRowA, minColA+size, maxColA, minRowB+size, maxRowB, minColB+size, maxColB);   //22 22
            //c= matrixAdd(c, 2, 2, size);

            return c;


            /*        _naive(partitionMatrix(a, 1, 2),  partitionMatrix(b, 2, 1)));
            int[][] c12 = matrixAdd(
                    _naive(partitionMatrix(a, 1, 1), partitionMatrix(b, 1, 2)),
                    _naive(partitionMatrix(a, 1, 2),  partitionMatrix(b, 2, 2)));
            int[][] c21 = matrixAdd(
                    _naive(partitionMatrix(a, 2, 1), partitionMatrix(b, 1, 1)),
                    _naive(partitionMatrix(a, 2, 2),  partitionMatrix(b, 2, 1)));
            int[][] c22 = matrixAdd(
                    _naive(partitionMatrix(a, 2, 1), partitionMatrix(b, 1, 2)),
                    _naive(partitionMatrix(a, 2, 2),  partitionMatrix(b, 2, 2)));

            return combinePartitions(c11, c12, c21, c22, c); */
        }
    }

    /**
     * Multiply two matrices by divide-and-conquer
     *
     * O(n^3)
     */
    public int[][] naive() {
        int[][] c = new int[a.length][a.length];
        return _naive(a,b,c,a.length,0,a.length-1,0,a.length-1,0,b.length-1,0,b.length-1);
        //System.out.println(Arrays.deepHashCode(c));
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
            int[][] P7 = _strassens(matrixSub(A12, A22), matrixAdd(B21, B22));   //index calc here
            int[][] C11 = matrixAdd(matrixSub(matrixAdd(P1, P4), P5), P7);    //reg add and sub here bc no partition
            int[][] C12 = matrixAdd(P3, P5);
            int[][] C21 = matrixAdd(P2, P4);
            int[][] C22 = matrixAdd(matrixAdd(matrixSub(P1, P2), P3), P6);

            return combinePartitions(C11, C12, C21, C22, c);
        }
    }
}
