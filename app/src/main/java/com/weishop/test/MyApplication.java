package com.weishop.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;

import com.github.moduth.blockcanary.BlockCanary;
import com.weishop.test.performance.AppBlockCanaryContext;
import com.weishop.test.util.TestUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyongchao on 16/7/1.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Debug.startMethodTracing(Environment.getExternalStorageDirectory() + "/test/testcpu.trace");
//        init();

//        Debug.stopMethodTracing();
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.out.println("onLowMemory");
        TestUtils.getMemoryInfo(this);
    }

    private void init() {
        testSleep();
    }

    private void testSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
