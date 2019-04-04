package com.test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        byte[] cacheData = new byte[100 * 1024 * 1024];
        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
        System.out.println("fist gc before cacheData=" + cacheData + ",cacheRef=" + cacheRef.get());
        System.gc();
        Thread.sleep(500);
        System.out.println("fist gc after cacheData=" + cacheData + ",cacheRef=" + cacheRef.get());

        cacheData = null;
        System.gc();

        Thread.sleep(500);

        System.out.println("last gc after cacheData=" + cacheData + ",cacheRef=" + cacheRef.get());
    }

}