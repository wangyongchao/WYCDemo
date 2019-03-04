package com.thinking.java.concurrency;//: concurrency/ExceptionThread.java
// {ThrowsException}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable {
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
        Thread.sleep(1000);
        System.out.println("dfsafd");
    }
} ///:~
