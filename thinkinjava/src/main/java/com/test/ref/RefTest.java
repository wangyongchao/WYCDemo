package com.test.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

class RefTest {

    public static void main(String[] args) {
        testSoftReference();

    }

    private static void testSoftReference() {
        ReferenceQueue<RefBean> referenceQueue = new ReferenceQueue<>();
        RefBean refBean = new RefBean();
        SoftReference<RefBean> softReference = new SoftReference<RefBean>(refBean, referenceQueue);
        refBean = null;
        System.gc();
        System.out.println("softReference=" + softReference.get());
        Reference<? extends RefBean> poll = referenceQueue.poll();
        System.out.println("referenceQueue=" + poll);

    }

}
