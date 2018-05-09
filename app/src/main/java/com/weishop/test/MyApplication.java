package com.weishop.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import com.weishop.test.util.TestUtils;

/**
 * Created by wangyongchao on 16/7/1.
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        TestUtils.applicationContext = this;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        System.out.println("MyApplication onActivityCreated activity=" + activity);

    }

    @Override
    public void onActivityStarted(Activity activity) {


    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        System.out.println("MyApplication onActivityDestroyed activity=" + activity);
    }
}
