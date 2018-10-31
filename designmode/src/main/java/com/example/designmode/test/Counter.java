package com.example.designmode.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chengxiao on 2017/3/18.
 */
public class Counter {
    public static volatile int num = 0;
    public static AtomicInteger numa = new AtomicInteger(0);
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);

    public static void main(String[] args) throws InterruptedException {
        //开启30个线程进行累加操作
        for (int i = 0; i < 30; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
//                        num++;//自加操作,不是原子操作
                        numa.incrementAndGet();//原子加操作
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        System.out.println(numa);
    }
}