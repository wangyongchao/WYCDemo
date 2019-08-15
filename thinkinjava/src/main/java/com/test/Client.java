package com.test;

public class Client {
    public static final String obj1 = "obj1";
    public static final String obj2 = "obj2";
    public static boolean condition = false;

    public static void main(String[] ars) {
        new DeadLockA().start();
        new DeadLockB().start();
    }
}