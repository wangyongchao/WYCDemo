package com.test.interview;

/**
 * 数组相关算法
 */

public class ArraysTest {

    public static void main(String[] args) {
        long[] aArr = {45, 33, 36, 22, 5, 1, 33, 60};
        multiply1(aArr);
        multiply2(aArr);


    }


    /**
     * 先把a[i]左边元素相乘，然后再把a[i]右边元素相乘
     * 构建乘积数组 b[i]=a[0]*a[1]*..a[i-1]*a[i+1]...a[n-1]
     *
     * @param arr
     */
    public static void multiply2(long[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        long[] bArr = new long[arr.length];
        for (int i = 0, product = 1; i < bArr.length; product *= arr[i], i++) {
            bArr[i] = product;
        }

        for (int i = arr.length - 1, product = 1; i >= 0; product *= arr[i], i--) {
            bArr[i] *= product;
        }

        print(bArr);

    }

    /**
     * 构建乘积数组 b[i]=a[0]*a[1]*..a[i-1]*a[i+1]...a[n-1]
     *
     * @param arr
     */
    public static void multiply1(long[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        long[] bArr = new long[arr.length];
        long muti = 1;
        for (int i = 0; i < arr.length; i++) {
            muti = muti * arr[i];
        }
        for (int j = 0; j < bArr.length; j++) {
            bArr[j] = muti / arr[j];
        }
        print(bArr);

    }


    private static void print(long[] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }

    }

}
