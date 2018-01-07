
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.weishop.test.R;


public class MyActivity extends Activity {

    private View mylinear;

    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyActivity onCreate");

        // // 设置无标题
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // // 设置全屏
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_myview);

        mylinear = findViewById(R.id.mylinear);

        View btn = findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });

        View imageview = findViewById(R.id.imageview);
        imageview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                n = 10;
                mylinear.scrollBy(20,0);
                int scrollY = mylinear.getScrollY();
                System.out.println("scrollX=" + scrollY);
            }
        });

        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

//                TestUtils.getProperty(MyActivity.this);

                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MyActivity onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MyActivity onDestroy");
    }
}
