package com.classloader.test;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerTest {

    private Lock lock = new ReentrantLock();

    private Condition addCondition = lock.newCondition();

    private Condition removeCondition = lock.newCondition();

    private LinkedList<Integer> resources = new LinkedList<>();

    private int maxSize;

    public ProducerConsumerTest(int maxSize) {
        this.maxSize = maxSize;
    }


    public class Producer implements Runnable {

        private int proSize;

        private Producer(int proSize) {
            this.proSize = proSize;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 1; i < proSize; i++) {
                    while (resources.size() >= maxSize) {
                        try {
                            addCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    resources.add(i);
                    removeCondition.signal();
                }
            } finally {
                lock.unlock();
            }

        }
    }

    public class Consumer implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (true) {
                lock.lock();
                try {
                    while (resources.size() <= 0) {
                        try {
                            // ��������״̬
                            System.out.println("enter await");
                            removeCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // ��������
                    int size = resources.size();
                    for (int i = 0; i < size; i++) {
                        Integer remove = resources.remove();
                    }
                    // ����������
                    addCondition.signal();
                } finally {
                    System.out.println("finally");
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerTest producerConsumerTest = new ProducerConsumerTest(10);
        Producer producer = producerConsumerTest.new Producer(100);
        Consumer consumer = producerConsumerTest.new Consumer();
        final Thread producerThread = new Thread(producer, "producer");
        final Thread consumerThread = new Thread(consumer, "consumer");
        producerThread.start();
        TimeUnit.SECONDS.sleep(2);
        consumerThread.start();
    }
}