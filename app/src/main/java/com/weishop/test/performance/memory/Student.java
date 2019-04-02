package com.weishop.test.performance.memory;

public class Student {

    //    new byte[1024 * 1024 * 380];
    private int[] a = new int[1024 * 1024];

    private String id;

    public Student(String id) {
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize" + this.id);
    }

    @Override
    public String toString() {
        return this.id;
    }
}