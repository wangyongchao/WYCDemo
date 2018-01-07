package com.test;


/**
 * Created by wangyongchao on 16/11/23.
 */

public class TestArrayList {


    public static void main(String[] args) {
        String s = "1000000000000000000000000000000000000000000000000000000000000000";

        byte a = Byte.MAX_VALUE;

        byte x = 123;
        byte y = 100;


        byte x1 = 0x7b;
        byte y1 = 0x64;

        byte z = (byte) (x + y);

        System.out.println(Integer.toBinaryString(x));


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
