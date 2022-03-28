package com.company;

import java.util.Arrays;
import java.util.Random;

public class Tests {
    public static final int GENERATE_MATRIX_MAX = 50;

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

    private static int[][] generateRandomMatrix(int n) {
        int[][] c = new int[n][n];

        Random random = new Random();

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                int value = random.nextInt(GENERATE_MATRIX_MAX) * (random.nextBoolean() ? 1 : -1);
                c[i][j] = value;
            }
        }

        return c;
    }

    private static long timeRunnable(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static void assertTraditional() {
        int[][] result = TestData.sanityMM.traditional();
        assert Arrays.deepEquals(result, TestData.RESULT) : "Traditional multiplication FAILED";

        System.out.println("Traditional multiplication verified");
    }

    private static void assertNaive() {
        int[][] result = TestData.sanityMM.naive();
        assert Arrays.deepEquals(result, TestData.RESULT) : "Naive multiplication FAILED";

        System.out.println("Naive multiplication verified");
    }

    private static void assertStrassens() {
        int[][] result = TestData.sanityMM.strassens();
        assert Arrays.deepEquals(result, TestData.RESULT) : "Strassens multiplication FAILED";

        System.out.println("Strassens multiplication verified");
    }

    private static void runAllTests_Power2(int times, int maxSizePower) {
        for (int power = 1; power < maxSizePower + 1; power++) {
            long tradAvgTime = 0L;
            long naiveAvgTime = 0L;
            long strasAvgTime = 0L;

            int n = (int) Math.pow(2, power);
            for (int count = 0; count < times; count++) {
                int[][] a = generateRandomMatrix(n);
                int[][] b = generateRandomMatrix(n);

                MatrixMultiplication mm = new MatrixMultiplication(a, b);

                long tradTime = timeRunnable(mm::traditional);
                tradAvgTime += tradTime;

                long naiveTime = timeRunnable(mm::naive);
                naiveAvgTime += naiveTime;

                long strasTime = timeRunnable(mm::strassens);
                strasAvgTime += strasTime;
            }

            tradAvgTime /= times;
            naiveAvgTime /= times;
            strasAvgTime /= times;

            System.out.printf(
                    """
                            ---- 2 RAISED TO:   %32d
                            AVG TRADITIONAL:    %32dms
                            AVG NAIVE:          %32dms
                            AVG STRASSENS:      %32dms
                            
                            """, power, tradAvgTime, naiveAvgTime, strasAvgTime);
        }
    }

    private static void runAllTests_AnyNum(int times, int maxSize) {
        for (int num = 1; num < maxSize + 1; num++) {
            long tradAvgTime = 0L;
            long naiveAvgTime = 0L;
            long strasAvgTime = 0L;

            for (int count = 0; count < times; count++) {
                int[][] a = generateRandomMatrix(num);
                int[][] b = generateRandomMatrix(num);

                MatrixMultiplication mm = new MatrixMultiplication(a, b);

                long tradTime = timeRunnable(mm::traditional);
                tradAvgTime += tradTime;

                long naiveTime = timeRunnable(mm::naive);
                naiveAvgTime += naiveTime;

                long strasTime = timeRunnable(mm::strassens);
                strasAvgTime += strasTime;
            }

            tradAvgTime /= times;
            naiveAvgTime /= times;
            strasAvgTime /= times;

            System.out.printf(
                    """
                            -Array Dimension:   %32d
                            AVG TRADITIONAL:    %32dms
                            AVG NAIVE:          %32dms
                            AVG STRASSENS:      %32dms
                            
                            """, num, tradAvgTime, naiveAvgTime, strasAvgTime);
        }
    }





    /**
     * Run unit tests to verify that everything worked as intended
     */
    public static void main(String[] args) {        
        System.out.println("RUNNING SANITY TESTS");
        assertTraditional();
        assertNaive();
        assertStrassens();
        System.out.println("SANITY PASSED");
        System.out.println();
        System.out.println("RUNNING RANDOMIZED TRAILS");
        runAllTests_AnyNum(30, 1000);
        runAllTests_Power2(30, 12);
        System.out.println("RANDOMIZED TRAILS PASSED");
        
    }
}