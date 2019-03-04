
package com.weishop.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.weishop.test.performance.PerformanceActivity;

public class LaunchActivity extends Activity {

    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        this.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchActivity.this.startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            }
        });
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

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
