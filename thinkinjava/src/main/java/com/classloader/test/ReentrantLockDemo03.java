package com.classloader.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo03 implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " success");
                TimeUnit.SECONDS.sleep(4);
            } else {
                System.out.println(Thread.currentThread().getName() + " faile");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        ReentrantLockDemo03 reentrantLockDemo = new ReentrantLockDemo03();
        Thread thread01 = new Thread(reentrantLockDemo, "thread01");
        Thread thread02 = new Thread(reentrantLockDemo, "thread02");
        thread01.start();
        thread02.start();
    }
}