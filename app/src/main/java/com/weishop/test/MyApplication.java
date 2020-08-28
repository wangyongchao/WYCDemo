package com.weishop.test;

import android.app.Application;
import android.content.Context;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.weishop.test.util.CrashHandler;

import java.io.File;

/**
 * Created by wangyongchao on 16/7/1.
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        PluginManager.getInstance(base).init();

    }

    @Override
    public void onCreate() {
        super.onCreate();

//        Debug.startMethodTracing(Environment.getExternalStorageDirectory() + "/test/testcpu.trace");
//        init();

//        Debug.stopMethodTracing();
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        CrashHandler.getInstance().init(this);

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
