package com.weishop.test.service;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

public class MyIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MyIntentService onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MyIntentService onDestroy");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            String name = intent.getStringExtra("name");
            System.out.println("thread=" + Thread.currentThread().getName() + "," + name);
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        System.out.println("startId="+startId);
        return super.onStartCommand(intent, flags, startId);
    }
}
