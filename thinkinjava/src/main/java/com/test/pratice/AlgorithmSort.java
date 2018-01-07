package com.test.pratice;

/**
 * Created by wangyongchao on 17/4/20.
 */

public class AlgorithmSort {

    public static void main(String[] args) {

        int elements[] = {80, 2, 56, 33, 20, 56, 22, 34, 78, 100};
        bubbleSort(elements);

    }


    /**
     * 冒泡排序<br/>
     * 稳定排序，时间复杂度为O(n2)
     *
     * @param a
     * @return
     */
    private static void bubbleSort(int a[]) {
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        printElements(a);
    }






    private static void printElements(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }


}
