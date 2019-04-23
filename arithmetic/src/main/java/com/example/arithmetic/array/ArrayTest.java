package com.example.arithmetic.array;

public class ArrayTest {

    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 0, 2, 5};
        int[] duplicates = new int[nums.length];
        duplicate(nums, nums.length, duplicates);
        print(duplicates);


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


}
