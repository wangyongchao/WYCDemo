package com.weishop.test.performance.memory;

import com.weishop.test.util.AppUtils;

public class Student {

    private byte[] bigSize = new byte[100 * AppUtils._1MB];

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