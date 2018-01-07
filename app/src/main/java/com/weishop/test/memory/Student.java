package com.weishop.test.memory;

public class Student {

    private int[] a = new int[1000];

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