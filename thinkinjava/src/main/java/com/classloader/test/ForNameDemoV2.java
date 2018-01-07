package com.classloader.test;

public class ForNameDemoV2 {
    public static void main(String[] args) {
        String property = System.getProperty("java.class.path");
        System.out.println(property);


    }
}