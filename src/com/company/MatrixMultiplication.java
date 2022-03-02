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



    private int[][] _naive(int[][] a, int[][] b) {
        
        int[][] c = new int[a.length][a.length];
        //System.out.println("row b:" + rowB);
        //System.out.println("col b:" + colB);
        if (a.length == 1) {
            c[0][0] = a[0][0] * b[0][0];
            

            /*for (int i = 0; i < c.length; i++) {
                for (int j = 0; j < c.length; j++) {
                    System.out.print(c[i][j] + "hi ");
                }
                System.out.println();
            }
            System.out.println("\n"); */
            return c;

        } else {
            //partitionMatrix(a, 1, 1);
            //partitionMatrix(a, 1, 2);
            //partitionMatrix(a, 2, 1);
            //partitionMatrix(a, 2, 2);
            //partitionMatrix(b, 1, 1);
            //partitionMatrix(b, 1, 2);
            //partitionMatrix(b, 2, 1);
            //partitionMatrix(b, 2, 2);
            int[][] c11 = Matrixadd(_naive(partitionMatrix(a, 1, 1), partitionMatrix(b, 1, 1)),
                _naive(partitionMatrix(a, 1, 2),  partitionMatrix(b, 2, 1)));      // TOP LEFT
            //System.out.println("---------------------------");
            int[][] c12 = Matrixadd(_naive(partitionMatrix(a, 1, 1), partitionMatrix(b, 1, 2)),
                _naive(partitionMatrix(a, 1, 2),  partitionMatrix(b, 2, 2)));   //TOP RIGHT
            int[][] c21 = Matrixadd(_naive(partitionMatrix(a, 2, 1), partitionMatrix(b, 1, 1)),
                _naive(partitionMatrix(a, 2, 2),  partitionMatrix(b, 2, 1)));             // BOT LEFT
            int[][] c22 = Matrixadd(_naive(partitionMatrix(a, 2, 1), partitionMatrix(b, 1, 2)),
            _naive(partitionMatrix(a, 2, 2),  partitionMatrix(b, 2, 2)));      // BOT RIGHT
            
            c = combinePartitons(c11, c12, c21, c22, c);
            /*System.out.println("HIIIII");
            for (int i = 0; i < c.length; i++) {
                for (int j = 0; j < c.length; j++) {
                    if (i <= half && j <= half) {
                        c[i][j] = c11[i][j];
                    }
                    else if (i <= half && j > half) {
                        c[i][j] = c12[i][j];
                    }
                    else if (i > half && j <= half) {
                        c[i][j] = c21[i][j];
                    }
                    else if (i > half && j > half) {
                        c[i][j] = c22[i][j];
                    }
                }
            }
            System.out.println(c.length); */
        
            /*for (int i = 0; i < c.length; i++) {
                for (int j = 0; j < c.length; j++) {
                    System.out.print(c[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n"); */

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
        //int partionLength= a.length / 2
        //int[][] test1= new int[partionLength][partionLength];
        //int[][] test2= new int[partionLength][partionLength];
        //int[][] test3= new int[partionLength][partionLength];
        //int[][] test4= new int[partionLength][partionLength];
        System.out.println("test!!: " + partitionMatrix(a, 1, 1).length);
        result = combinePartitons(partitionMatrix(a, 1, 1), partitionMatrix(a, 1, 2), partitionMatrix(a, 2, 1),
            partitionMatrix(a, 2, 2), result);
        
        Matrixadd(a, b);

        //partitionMatrix(a, 1, 1);
        //partitionMatrix(a, 1, 2);
        //partitionMatrix(a, 2, 1);
        //partitionMatrix(a, 2, 2);
        


        //return result;
        
        result = _naive(a,b);
        System.out.println("\n");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        return result;
        //return _naive(a, b);
    }

    private int[][] Matrixadd(int a[][], int b[][]) {
        int[][] c = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        System.out.println("\n");
        /*for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        } */

        return c;
    }

    private int[][] Matrixsub(int a[][], int b[][]) {
        int[][] c = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        System.out.println("\n");
        /*for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        } */

        return c;
    }

    private int[][] combinePartitons(int[][] c11, int[][] c12, int[][] c21, int[][] c22, int[][] c) {
        int length = c11.length;
        //System.out.println("small array: " + c11.length);
        for (int i = 0; i < c11.length; i++) {
            for (int j = 0; j < c11.length; j++) {
                //System.out.println("i:" + i + " j:" + j);
                c[i][j] = c11[i][j];
                //System.out.println(j+length);
                c[i][j+length] = c12[i][j];
                //System.out.println("test2");
                c[i+length][j] = c21[i][j];
                //System.out.println("test3");
                c[i+length][j+length] = c22[i][j];
                //System.out.println("test4");
            }
        }

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                System.out.print(c[i][j]);
            }
            System.out.println();
        }
        

        return c;
    }

    private int[][] partitionMatrix(int[][] A, int row, int col) {
        int length = A.length / 2;
        int[][] partitionA = new int[length][length];
        //System.out.println("small part array:" + partitionA.length);
        //int[] A_linear = new int[a.length*a.length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (row == 1 & col == 1) {
                    partitionA[i][j]= A[i][j];
                }
                if (row == 1 & col == 2) {
                    partitionA[i][j]= A[i][j+length];
                }
                if (row == 2 & col == 1) {
                    partitionA[i][j]= A[i+length][j];
                }
                if (row == 2 & col == 2) {
                    partitionA[i][j]= A[i+length][j+length];
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(partitionA[i][j]);
            }
            System.out.println();
        }
        return partitionA;

    }

    /**
     * Multiply two matrices using Strassen's method
     *
     * O(n^2.8)
     */
    public int[][] strassens(int[][] A, int[][] B) {

        int[][] C = new int[A.length][A.length];
    
        if (A.length == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }
        else {
            //int newLength = A.length;
            int[][] A11 = partitionMatrix(A, 1, 1);
            int[][] A12 = partitionMatrix(A, 1, 2);
            int[][] A21 = partitionMatrix(A, 2, 1);
            int[][] A22 = partitionMatrix(A, 2, 2);

            int[][] B11 = partitionMatrix(B, 1, 1);
            int[][] B12 = partitionMatrix(B, 1, 2);
            int[][] B21 = partitionMatrix(B, 2, 1);
            int[][] B22 = partitionMatrix(B, 2, 2);

            int[][] P1 = strassens(Matrixadd(A11, A22), Matrixadd(B11, B22));
            int[][] P2 = strassens(Matrixadd(A21, A22), B11);
            int[][] P3 = strassens(A11, Matrixsub(B12, B22));
            int[][] P4 = strassens(A22, Matrixsub(B21, B11));
            int[][] P5 = strassens(Matrixadd(A11, A12), B22);
            int[][] P6 = strassens(Matrixsub(A21, A11), Matrixadd(B11, B12));
            int[][] P7 = strassens(Matrixsub(A12, A22), Matrixadd(B21, B22));
            int[][] C11 = Matrixadd(Matrixsub(Matrixadd(P1, P4), P5), P7);
            int[][] C12 = Matrixadd(P3, P5);
            int[][] C21 = Matrixadd(P2, P4);
            int[][] C22 = Matrixadd(Matrixadd(Matrixsub(P1, P2), P3), P6);

            C = combinePartitons(C11, C12, C21, C22, C);

            System.out.println("\n");
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C.length; j++) {
                    System.out.print(C[i][j] + " ");
                }
                System.out.println();
            } 
            return C;
        }
    }
}
