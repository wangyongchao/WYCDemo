package com.test.pratice;

import java.util.Arrays;
import java.util.Random;

/**
 * <一句话功能简述>
 */
public class AllSort {

    /**
     * 快速排序<br/>
     * 不稳定排序，时间复杂度O(nlogn)
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static void quickSort(int[] a, int left, int right) {
        if (left >= right)
            return;

        int i, j, temp;
        i = left;
        j = right;
        temp = a[left];

        while (i != j) {
            while (a[j] >= temp && i < j)
                j--;

            while (a[i] <= temp && i < j)
                i++;

            if (i < j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                j--;
            }
        }

        a[left] = a[i];
        a[i] = temp;

        if (left < i - 1)
            quickSort(a, left, i - 1);
        if (i + 1 < right)
            quickSort(a, i + 1, right);
    }

    /**
     * 选择排序<br/>
     * 不稳定排序，时间复杂度为O(n2)
     *
     * @param a
     * @return
     */
    public static void choseSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[min_index] > a[j]) {
                    min_index = j;
                }
            }
            if (i != min_index) {
                int temp = a[i];
                a[i] = a[min_index];
                a[min_index] = temp;
            }
        }
    }

    /**
     * 冒泡排序<br/>
     * 稳定排序，时间复杂度为O(n2)
     *
     * @param a
     * @return
     */
    public static void bubbleSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 直接插入排序<br/>
     * 稳定排序，时间复杂度O(n2)
     *
     * @param a
     * @return
     */
    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int pos = i - 1;
            while (pos >= 0 && a[pos] > temp) {
                a[pos + 1] = a[pos];
                pos--;
            }
            a[pos + 1] = temp;
        }
    }

    /**
     * 希尔排序<br/>
     * 不稳定排序，时间复杂度O(nlogn)
     *
     * @param a
     * @return
     */
    public static void shellSort(int[] a) {
        int d = a.length / 2;
        while (d >= 1) {
            for (int i = 0; i < d; i++) {
                for (int j = i + d; j < a.length; j = j + d) {
                    int temp = a[j];
                    int pos = j - d;
                    while (pos >= 0 && a[pos] > temp) {
                        a[pos + d] = a[pos];
                        pos -= d;
                    }
                    a[pos + d] = temp;
                }
            }
            d = d / 2;
        }

    }

    /**
     * 归并排序<br/>
     * 稳定排序,时间复杂度O(nlogn),速度仅次于快速排序
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            // 对左边进行递归
            mergeSort(a, left, middle);
            // 对右边进行递归
            mergeSort(a, middle + 1, right);
            // 合并
            merge(a, left, middle, right);
        }
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int[] tmpArr = new int[right - left + 1];
        int pos = 0;

        int i = left;// 左边起始位置
        int j = middle + 1; // 右边起始位置
        while (i <= middle && j <= right) {
            // 从两个数组中选取较小的数放入中间数组
            if (a[i] <= a[j]) {
                tmpArr[pos++] = a[i++];
            } else {
                tmpArr[pos++] = a[j++];
            }
        }
        // 将剩余的部分放入中间数组
        while (i <= middle) {
            tmpArr[pos++] = a[i++];
        }
        while (j <= right) {
            tmpArr[pos++] = a[j++];
        }
        // 将中间数组复制回原数组
        int start = 0;
        while (left <= right) {
            a[left++] = tmpArr[start++];
        }
    }

    /**
     * 堆排序<br/>
     * 不稳定排序，时间复杂度O(nlogn)
     *
     * @param a
     * @return
     */
    public static void heapSort(int[] a) {
        int lastIndex = a.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            // 建堆
            buildMaxHeap(a, lastIndex - i);
            // 交换堆顶和最后一个元素
            swap(a, 0, lastIndex - i);
            // System.out.println(Arrays.toString(a));
        }
    }

    private static void buildMaxHeap(int[] data, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // k保存正在判断的节点
            int k = i;
            // 如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                // k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                // 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    // 若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        // biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                // 如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    // 交换他们
                    swap(data, k, biggerIndex);
                    // 将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] a = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(20);
        }

        //quickSort(a, 0, a.length - 1);
        //mergeSort(a, 0, a.length - 1);
        //shellSort(a);
        //heapSort(a);
        //choseSort(a);
        //bubbleSort(a);
        //insertSort(a);
        System.out.println(Arrays.toString(a));

    }

}