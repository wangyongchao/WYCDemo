package com.test;

/**
 * Created by wangyongchao on 2017/7/24.
 */

public class Algorithm {

    public static void main(String[] args) {

        int[] arr = {45, 33, 36, 22, 5, 1, 33, 60};
        print(arr);
        xier_sort1(arr);
        print(arr);


    }

    /**
     * 冒泡排序
     * 时间复杂度 O(n^2)
     *
     * @param arr
     */
    private static void bubble_sort(int[] arr) {
        int temp;

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        }

    }

    /**
     * 选择排序
     * 时间复杂度 O(n^2)
     *
     * @param arr
     */
    private static void select_sort(int[] arr) {
        int temp;

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    /**
     * 插入排序
     *
     * @param arr 时间复杂度 O(n^2)
     */
    private static void inser_sort(int[] arr) {
        int inserNote, i, j;

        for (i = 1; i < arr.length; i++) {
            inserNote = arr[i];
            j = i - 1;
            while (j >= 0 && inserNote < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = inserNote;
        }
    }


    /**
     * 希尔排序是插入排序一种，只不过步长不是1，减少移动的次数，提高效率
     * @param arr {45, 33, 36, 22, 5, 1, 33, 60}
     *            5，1，33，36，22，45，33，60
     *            33，1，5，36，22，45，33，60
     *            1，5，22，33，36，45，
     *
     */
    public static void xier_sort1(int[] arr) {
        int j = 0;
        int insertNote = 0;
        for (int increment = arr.length / 2; increment > 0; increment /= 2) {
            for (int i = increment; i < arr.length; i++) {//increment 4
                insertNote = arr[i];
                j = i - increment;
                while (j >= 0 && insertNote < arr[j]) {
                    arr[j + increment] = arr[j];
                    j = j - increment;
                }
                arr[j + increment] = insertNote;
            }
        }
    }


    /**
     * @param arr {45, 33, 36, 22, 5, 1, 33, 60}
     */
    public static void xier_sort(int[] arr) {
        int j = 0;
        int temp = 0;
        for (int increment = arr.length / 2; increment > 0; increment /= 2) {
            for (int i = increment; i < arr.length; i++) {//increment 4
                temp = arr[i];
                for (j = i - increment; j >= 0; j -= increment) {
                    if (temp < arr[j]) {
                        arr[j + increment] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + increment] = temp;
            }
        }
    }


    private static void print(int[] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }

    }

}
