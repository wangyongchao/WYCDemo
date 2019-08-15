package com.test;


public class DeadLockA extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("LockA running");
            while (true) {
                synchronized (Client.obj1) {
                    System.out.println("LockA locked obj1");
                    //获取obj1后先等一会儿，让LockB有足够的时间锁住obj2
                    Thread.sleep(100);
                    System.out.println("LockA trying to lock obj2...");
                    if (Client.condition) {
                        wait();
                    }
                    synchronized (Client.obj2) {
                        System.out.println("LockA locked obj2");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}