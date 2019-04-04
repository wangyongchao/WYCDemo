package com.weishop.test.performance.memory;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;

public class Test {
    public static ReferenceQueue<Student> queue = new ReferenceQueue<Student>();

    public static void main(String[] args) {

        testGC();

    }

    public static void testGC() {
        HashSet<SoftReference<Student>> hs1 = new HashSet<SoftReference<Student>>();
        HashSet<WeakReference<Student>> hs2 = new HashSet<WeakReference<Student>>();
        for (int i = 1; i <= 10; i++) {
            SoftReference<Student> soft = new SoftReference<Student>(new Student("soft" + i), queue);
            System.out.println("create soft" + soft.get());
            hs1.add(soft);
        }
        System.gc();
        checkQueue();

//        for (int i = 1; i <= 10; i++) {
//            WeakReference<Student> weakReference = new WeakReference<Student>(new Student("weak" + i), queue);
//            System.out.println("create weakReference" + weakReference.get());
//            hs2.add(weakReference);
//        }

//        System.gc();
//        checkQueue();


    }

    public static void checkQueue() {
        if (queue != null) {
            @SuppressWarnings("unchecked")
            Reference<Student> ref = (Reference<Student>) queue.poll();
            while ( ref != null){
                System.out.println(ref + "......" + ref.get());
                ref= (Reference<Student>) queue.poll();
            }

        }
    }


    public static void testWeakReference() {
        String str = "test";

        WeakReference<String> weakReference = new WeakReference<String>(str);
        str = null;
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(weakReference.get());
    }

    public static void testReference() {
        String str = "test";

        SoftReference<String> sf = new SoftReference<String>(str);
        str = null;
        System.out.println(sf.get());
    }
}