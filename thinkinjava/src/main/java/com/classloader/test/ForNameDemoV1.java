package com.classloader.test;

public class ForNameDemoV1 {
    public static void main(String[] args) {
        try {
            System.out.println("载入TestClass");
            Class c = Class.forName("com.classloader.test.TestClass",true,Thread.currentThread().getContextClassLoader());

            System.out.println("使用TestClass宣告参考名称");
            TestClass test = null;

            System.out.println("使用TestClass建立物件");
            test = new TestClass();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的类别");
        }
    }
}