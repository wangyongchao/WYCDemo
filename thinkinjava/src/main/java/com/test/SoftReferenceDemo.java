package com.test;

import java.lang.ref.SoftReference;

/**
 * 内存不足的时候回收
 */
public class SoftReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        byte[] cacheData = new byte[100 * 1024 * 1024];
        SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
        cacheData = null;
        System.out.println("fist gc before cacheData=" + cacheData + ",cacheRef=" + cacheRef.get());
        System.gc();
        Thread.sleep(500);
        System.out.println("fist gc after cacheData=" + cacheData + ",cacheRef=" + cacheRef.get());

        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("new area cacheData=" + cacheData + ",cacheRef=" + cacheRef.get());
    }

}