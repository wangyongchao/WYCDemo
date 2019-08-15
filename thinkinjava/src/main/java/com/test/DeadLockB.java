package com.test;


public class DeadLockB extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("LockB running");
            while (true) {
                synchronized (Client.obj2) {
                    System.out.println("LockB locked obj2");
                    System.out.println("LockB trying to lock obj1...");
                    if (!Client.condition) {
                        wait();
                    }
                    synchronized (Client.obj1) {
                        System.out.println("LockB locked obj1");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
