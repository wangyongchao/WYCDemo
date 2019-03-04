package com.thinking.java.concurrency.demo;

import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    ReentrantLock reentrantLock = new ReentrantLock(true);

    // 当前拥有的票数
    private int num = 100;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            reentrantLock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
            }
            reentrantLock.unlock();

        }
    }

    public static void main(String[] args) {
        Ticket t = new Ticket();//创建一个线程任务对象。
        //创建4个线程同时卖票
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        //启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
