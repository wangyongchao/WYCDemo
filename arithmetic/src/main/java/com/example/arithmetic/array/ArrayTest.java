package com.example.arithmetic.array;

public class ArrayTest {

    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 3, 2, 3, 6, 1};
        int[][] arrays = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int[] clone = nums.clone();
        System.out.println(nums==clone);
//        int[] results = new int[nums.length];
//        multiply(nums, results);
//        print(results);
//        int[] duplicates = new int[nums.length];
//        duplicate(nums, nums.length, duplicates);
//        print(duplicates);
    }

    /**
     * 构建乘积数组
     *
     * @param nums
     */
    public static void multiply(int[] nums, int reuslts[]) {
        for (int i = 0, product = 1; i < nums.length; i++) {
            reuslts[i] = product;
            product = product * nums[i];
        }

        for (int j = nums.length - 1, product = 1; j >= 0; j--) {
            reuslts[j] = reuslts[j] * product;
            product = product * nums[j];
        }
    }


    /**
     * 给定一个二维数组，其每一行从左到右递增排序，
     * 从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
     * 时间复杂度O(M + N)
     *
     * @param target
     * @param matrix
     * @return
     */
    public static boolean find(int target, int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = 0;
        int col = matrix[0].length - 1;
        while (row <= matrix.length - 1 && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (target > matrix[row][col]) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }

    /**
     * 找出数组中所有重复的数字
     *
     * @param nums
     * @param results
     */
    public static void duplicate2(int[] nums, int[] results) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    results[j++] = nums[i];
                    break;
                }
                swap(nums, i, nums[i]);
            }

        }
    }


    /**
     * 查找数组中重复的数字
     *
     * @param nums
     * @param length
     * @param duplicates
     * @return
     */
    public static boolean duplicate(int nums[], int length, int duplicates[]) {
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplicates[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }


        return false;
    }


    public static void swap(int num[], int i, int j) {
        int t = num[i];
        num[i] = num[j];
        num[j] = t;
    }

    public static void print(int num[]) {
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + ",");
        }
    }

    public static void printTwoDimensionalArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
        }
    }


}
