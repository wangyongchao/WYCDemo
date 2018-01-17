
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.weishop.test.R;


public class MyActivity extends Activity {

    private View mylinear;

    private int n = 0;
    private View imageview;
    private int distance;
    private View myView;

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
        myView = findViewById(R.id.myview);

        View btn = findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int left = myView.getLeft();
                int top = myView.getTop();
                System.out.println("left=" + left + ",top=" + top);
                testScroll();
            }

        });

        imageview = findViewById(R.id.imageview);
        imageview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void testScroll() {
        //正数可视区域向下，向右移动相当子view向上，向左移动。负数相反
        int scrollX = mylinear.getScrollX();
        int scrollY = mylinear.getScrollY();
        System.out.println("scrollX=" + scrollX + ",scrollY=" + scrollY);
//        testScrollto();
        testScrollto();

    }


    private void testScrollto() {
        distance = distance + 10;
        mylinear.scrollTo(distance, 0);//可视区域移动
        int scrollX = mylinear.getScrollX();
        int scrollY = mylinear.getScrollY();
        System.out.println("after scrollX=" + scrollX + ",scrollY=" + scrollY);

        int left = myView.getLeft();
        int top = myView.getTop();
        System.out.println("after left=" + left + ",top=" + top);

    }

    private void testScrollby() {
        distance = 0 - 10;
        mylinear.scrollBy(0, distance);//可视区域移动

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
