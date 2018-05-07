package com.test;


/**
 * Created by wangyongchao on 16/11/23.
 */

public class TestArrayList {


    public static void main(String[] args) {
       while(true){
           System.out.println("dfsads");

       }

    }

    public void checkedAdd() {
//        long result = a + b;
//
//        return checkNoOverflow(result, (a ^ b) < 0 | (a ^ result) >= 0);
    }

    public long checkNoOverflow(long result, boolean condition) {
        return condition ? result : Long.MAX_VALUE;
    }

    public void test() {
        String[] s = new String[10000000];
        for (int i = 0; i < s.length; i++) {
            s[i] = "" + i;
        }
        String[] news = new String[s.length];

        long l = System.currentTimeMillis();
        for (int i = 0; i < s.length; i++) {
            news[i] = s[i];
        }
        System.out.println(System.currentTimeMillis() - l);

        l = System.currentTimeMillis();
        System.arraycopy(s, 0, news, 0, s.length);
        System.out.println(System.currentTimeMillis() - l);
    }
}
