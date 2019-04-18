package com.test.interview;

/**
 * 字符串相关算法
 */

public class StringTest {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("ab cde  f");
        System.out.println(replaceSpace(stringBuffer));
    }




    /**
     * 替换字符串中的空格为%20
     * 首先填充原来的字符串和替换后的长度相同
     * 然后设置两个指针p1,p2 ，其中p1指向原来字符串的长度，p2指向新的字符串的长度
     * 判断p1指向的字符串是否是空格，如果是就把p2指向的位置依次替换为02%,如果不是就替换为p1指向的字符
     * 这样当p2<=p1或者p1<0的时候就代表替换完了。
     *
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        int p1 = str.length() - 1;
        for (int i = 0; i <= p1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");

        int p2 = str.length() - 1;
        while (p1 >= 0 && p2 > p1) {
            char c = str.charAt(p1--);
            if (c == ' ') {
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            } else {
                str.setCharAt(p2--, c);
            }
        }
        return str.toString();
    }


}
