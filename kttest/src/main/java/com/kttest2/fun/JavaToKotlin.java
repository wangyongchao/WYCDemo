package com.kttest2.fun;

import java.util.ArrayList;

import strings.StringFunctions;

public class JavaToKotlin {
    public static void test() {
        testCallField();

    }

    /**
     * 调用扩展函数
     */
    public static void testCallField() {
        char dfadsf = StringFunctions.getLastCharFiled("dfadsf");
        System.out.println(dfadsf);
    }

    /**
     * 调用扩展函数
     */
    public static void testCallExt() {
       StringFunctions.lastChar("java");
    }



    /**
     * Java 调用kotlin顶层属性
     */
    public static void testCallTopField() {
      StringFunctions.performOperation();
      StringFunctions.reportOperationCount();
    }


    /**
     * Java 调用kotlin顶层函数
     */
    public static void testCallTopFun() {
        ArrayList<Integer> a = new ArrayList();
        a.add(2);
        a.add(3);
        a.add(4);
        String result = StringFunctions.joinToString(a, ", ", "(", ")");
        System.out.println(result);
    }
}
