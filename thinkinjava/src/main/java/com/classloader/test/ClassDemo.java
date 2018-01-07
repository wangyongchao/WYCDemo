package com.classloader.test;

public class ClassDemo {
    public static void main(String[] args) {
        Integer name = 2;
        Class stringClass = int.class;
        System.out.println("类别名称：" +
                stringClass.getName());
        System.out.println("是否为介面：" +
                stringClass.isInterface());
        System.out.println("是否为基本型态：" +
                stringClass.isPrimitive());
        System.out.println("是否为阵列物件：" +
                stringClass.isArray());
        System.out.println("父类别名称：" +
                stringClass.getSuperclass().getName());
    }
} 