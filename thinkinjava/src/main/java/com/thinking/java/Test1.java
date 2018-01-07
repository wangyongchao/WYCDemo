
package com.thinking.java;

import java.util.Date;

/**
 * Created by wangyongchao on 16/2/29.
 */
public class Test1 extends Date {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        System.out.println(Test1.class.getClassLoader());

    }

    static int test1() {
        int x = 1;
        try {
            return x;
        } finally {
            ++x;
            System.out.println("x="+x);
        }
    }

    public static void test() {
        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";
        System.out.println(s2 == "ab");
        System.out.println(s3 == "ab");

    }

}

class Test2 {
    public void test(String type) {

        switch (type) {

            case "情况A":

                break;

        }

    }
}
