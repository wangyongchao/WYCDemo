package com.test;

import com.thinking.java.concurrency.CachedThreadPool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThread {

    public static void main(String[] args) {



    }

    public void testSynchronees() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("take before");
                    String take = synchronousQueue.take();
                    System.out.println("take after =" + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronousQueue.offer("111");

        synchronousQueue.offer("222");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String poll = synchronousQueue.poll();
        System.out.println("poll=" + poll);
    }

    public static void testThreadPoll() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 2, 60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111111111" + Thread.currentThread().getName());
            }
        });


        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("22222222222222222" + Thread.currentThread().getName());
            }
        });


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("33333333333333" + Thread.currentThread().getName());
            }
        });

    }
}
