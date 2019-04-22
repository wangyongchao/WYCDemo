package com.weishop.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.didi.virtualapk.PluginManager;
import com.github.moduth.blockcanary.BlockCanary;
import com.weishop.test.performance.AppBlockCanaryContext;
import com.weishop.test.util.TestUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyongchao on 16/7/1.
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();

    }

    @Override
    public void onCreate() {
        super.onCreate();

//        Debug.startMethodTracing(Environment.getExternalStorageDirectory() + "/test/testcpu.trace");
//        init();

//        Debug.stopMethodTracing();
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();

    }

    private void initLog() {
        LoganConfig config = new LoganConfig.Builder()
                .setCachePath(getApplicationContext().getFilesDir().getAbsolutePath())
                .setPath(getApplicationContext().getExternalFilesDir(null).getAbsolutePath()
                        + File.separator + "logan_v1")
                .setEncryptKey16("0123456789012345".getBytes())
                .setEncryptIV16("0123456789012345".getBytes())
                .build();
        Logan.init(config);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.out.println("onLowMemory");
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
