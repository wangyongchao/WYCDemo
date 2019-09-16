
package com.weishop.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LaunchActivity extends Activity {

    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LaunchActivity.this.startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            }
        };
//        mHandler.sendEmptyMessageDelayed(0, 1000);

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        System.out.println("LaunchActivity onAttachedToWindow");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("LaunchActivity onResume");
    }
}
