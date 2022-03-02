package com.company;

import java.util.Arrays;

public class Tests {

    /**
     * Test data to use
     */
    public static class TestData {
        public static final int[][] A = {
                {2, 0, -1, 6},
                {3, 7, 8, 0},
                {-5, 1, 6, -2},
                {8, 0, 2, 7}
        };

        public static final int[][] B = {
                {0, 1, 6, 3},
                {-2, 8, 7, 1},
                {2, 0, -1, 0},
                {9, 1, 6, -2}
        };

        public static final int[][] RESULT = {
                {52, 8, 49, -6},
                {2, 59, 59, 16},
                {-8, 1, -41, -10},
                {67, 15, 88, 10}
        };

        /**
         * Matrix Multiplication class instance using our sanity data
         */
        public static MatrixMultiplication sanityMM = new MatrixMultiplication(A, B);
    }

    private static void assertTraditional() {
        int[][] result = TestData.sanityMM.traditional(TestData.A, TestData.B);
        assert Arrays.deepEquals(result, TestData.RESULT) : "Traditional multiplication FAILED";

        System.out.println("Traditional multiplication verified");
    }

    private static void assertNaive() {
        int[][] result = TestData.sanityMM.naive();
        assert Arrays.deepEquals(result, TestData.RESULT) : "Naive multiplication FAILED";

        System.out.println("Naive multiplication verified");
    }

    private static void assertStrassens() {
        int[][] result = TestData.sanityMM.strassens(TestData.A, TestData.B);
        assert Arrays.deepEquals(result, TestData.RESULT) : "Strassens multiplication FAILED";

        System.out.println("Strassens multiplication verified");
    }

    /**
     * Run unit tests to verify that everything worked as intended
     */
    public static void main(String[] args) {
        //assertTraditional();
        //assertNaive();
        assertStrassens();
    }

}
