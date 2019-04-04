package com.weishop.test.performance.memory;

import com.weishop.test.util.AppUtils;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 虚拟机参数：-verbose:gc
 */
public class ReferenceCountingGC {
    private Object instance = null;

    private Student student = new Student("111");
    private WeakReference<Student> weakReference = new WeakReference<Student>(student);

//
//    /** 这个成员属性唯一的作用就是占用一点内存 */
//    private byte[] bigSize = new byte[2 * AppUtils._1MB];

    public static void main(String[] args) {
        System.out.println("main");
        ReferenceCountingGC objectA = new ReferenceCountingGC();
        ReferenceCountingGC objectB = new ReferenceCountingGC();
        objectA.instance = objectB;
        objectB.instance = objectA;
        objectA = null;
        objectB = null;
    }
}