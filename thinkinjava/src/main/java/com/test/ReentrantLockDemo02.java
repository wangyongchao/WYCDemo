package com.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo02 implements Runnable {

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " running");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " finished");
            lock.unlock();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo02 reentrantLockDemo = new ReentrantLockDemo02();
        Thread thread01 = new Thread(reentrantLockDemo, "thread01");
        Thread thread02 = new Thread(reentrantLockDemo, "thread02");
        thread01.start();
        thread02.start();
        thread02.interrupt();
    }
}