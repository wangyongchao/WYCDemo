package com.example.arithmetic.string;

import java.lang.String;

/**
 * Created by wangyongchao on 2018/5/30.
 * 测试字符串
 */

public class StringTest {
    public static void main(String[] args) {
//        String string = "abcedfg";
//        String subString = "dfg";
//        System.out.println(indexof(string, subString, 0));
//        getNext("aaaaaaaab");
        System.out.println(0 % 3);


    }


    public static int[] getNext(String T) {
        int[] next = new int[T.length()];
        int i = 0;
        int j = -1;

        next[i] = j;

        while (i < T.length() - 1) {

            if (j == -1 || T.charAt(i) == T.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];//若字符不相等，则j值进行回溯。
            }
        }
        for (int k = 0; k < next.length; k++) {
            System.out.print(next[k]);
        }
        System.out.println();
        return next;
    }


    /**
     * 查找子串位置
     *
     * @param subString
     * @param pos
     */
    private static int indexof(String str, String subString, int pos) {
        int mainLen = str.length();
        int subLen = subString.length();
        int i = 0, j = 0;
        while (i < mainLen && j < subLen) {
            char mainChar = str.charAt(i);
            char subChar = subString.charAt(j);
            if (mainChar == subChar) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= subLen) {
            return i - j;
        }
        return -1;

    }


}
