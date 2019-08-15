package com.test;

public class SynchronizedDemo03 implements Runnable {


    @Override
    public void run() {
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " running");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " finished");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo03 reentrantLockDemo = new SynchronizedDemo03();
        Thread thread01 = new Thread(reentrantLockDemo, "thread01");
        Thread thread02 = new Thread(reentrantLockDemo, "thread02");
        thread01.start();
        thread02.start();
        thread02.interrupt();
    }
}