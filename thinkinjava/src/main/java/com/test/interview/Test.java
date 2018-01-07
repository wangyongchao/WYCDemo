package com.test.interview;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangyongchao on 2017/8/11.
 */

public class Test {

    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance();
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);

        System.out.println(month + "," + day+","+hour+","+minute);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        System.out.println(date);


    }

    static class MyRunnable implements Runnable {


        @Override
        public void run() {

            synchronized (Test.class) {
                try {
                    System.out.println("name before=" + Thread.currentThread().getName());
                    Test.class.wait();
                    System.out.println("name=" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    static class MyRunnable1 implements Runnable {

        @Override
        public void run() {

            synchronized (Test.class) {
                try {
                    System.out.println("name before=" + Thread.currentThread().getName());
                    Test.class.notify();
                    Thread.sleep(500);
                    System.out.println("name=" + Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
