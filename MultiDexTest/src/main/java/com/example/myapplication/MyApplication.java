package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.lib.MultiDex;

/**
 * Created by wangyongchao on 16/7/1.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
