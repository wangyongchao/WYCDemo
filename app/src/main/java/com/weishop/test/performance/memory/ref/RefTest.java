package com.weishop.test.performance.memory.ref;

import com.weishop.test.util.LogUtils;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RefTest {
    ReferenceQueue referenceQueue = new ReferenceQueue();
    private List<RefBean> refBeanList = new ArrayList<>();
    private SoftReference<RefBean> softReference;
    private WeakReference<RefBean> weakReference;
    private PhantomReference<RefBean> phantomReference;
    private RefBean refBean;


    /**
     * 测试软引用
     * 先调用testSoftReference
     * 然后频繁调用testObjectReference往list中添加数据，每次增加100M
     * 当当前app堆申请已经到达最大界限，无法申请的时候，
     * Alloc concurrent copying GC freed 379(79KB) AllocSpace objects, 0(0B) LOS objects,
     * 2% free,
     * 500MB/512MB, paused 308us total 11.143ms
     * 当垃圾回收发生后，当前堆内存已占用500M，当前堆内存总大小是512M，这时候申请100M,无法分配内存，分配内存的线程
     * 就会触发 Forcing collection of SoftReferences for 100MB
     * allocation，强制回收被SoftReferences引用的对象。
     * Alloc concurrent copying GC freed 58(33KB) AllocSpace objects, 1(100MB) LOS
     * objects, 5% free,
     * 400MB/424MB, paused 233us total 11.900ms
     * 软引用被回收后，回收了1(100MB) LOS objects 一个100M的大对象。
     * SoftReferences是在内存不足的情况下，会回收所关联的对象。
     */
    public void testSoftReference() {
        RefBean refBean = new RefBean();
        //当SoftReference所关联的对象被回收后，会把SoftReference放入引用队列中。
        softReference = new SoftReference<RefBean>(refBean, referenceQueue);
    }


    /**
     * 测试弱引用
     */
    public void testWeakReference() {
        RefBean refBean = new RefBean();
        //当SoftReference所关联的对象被回收后，会把SoftReference放入引用队列中。
        weakReference = new WeakReference<>(refBean, referenceQueue);
    }

    /**
     * 测试虚引用
     */
    public void testPhantomReference() {
        refBean = new RefBean();
        //当SoftReference所关联的对象被回收后，会把SoftReference放入引用队列中。
        phantomReference = new PhantomReference<>(refBean, referenceQueue);
    }


    /**
     * 测试内存增长
     */
    public void testMemoryGrowth() {
        RefBean refBean = new RefBean();
        refBeanList.add(refBean);
    }


    public void startCheckQueue() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int cnt = 0;
                    Reference k;
                    LogUtils.d("startCheckQueue");
                    //remove 阻塞知道引用队列中有元素
                    while ((k = referenceQueue.remove()) != null) {
                        LogUtils.d((cnt++) + "回收了:" + k);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
