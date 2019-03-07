
package com.weishop.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weishop.test.activitycharacter.AActivity;
import com.weishop.test.custom.CustomActivity;
import com.weishop.test.custom.MyActivity;
import com.weishop.test.memory.LeakActivity;
import com.weishop.test.performance.PerformanceActivity;
import com.weishop.test.util.FeedBackWindowManager;

public class MainActivity extends Activity implements View.OnClickListener {

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private EditText editText;

    private TextView mTextview;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        int has = ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW);
//        if(has== PackageManager.PERMISSION_GRANTED){
//            System.out.println("PERMISSION_GRANTED");
//        }else if(has==PackageManager.PERMISSION_DENIED){
//            System.out.println("PERMISSION_DENIED");
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 12345);
//        }

        setContentView(R.layout.activity_main);

        findViewById(R.id.linear_btn).setOnClickListener(this);
        findViewById(R.id.frame_btn).setOnClickListener(this);
        findViewById(R.id.relative_btn).setOnClickListener(this);
        findViewById(R.id.myview_btn).setOnClickListener(this);
        findViewById(R.id.animate_btn).setOnClickListener(this);
        findViewById(R.id.listview_btn).setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageview);
        mTextview = (TextView) findViewById(R.id.text);
        mTextview.setOnClickListener(this);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        System.out.println("onSaveInstanceState PersistableBundle");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class MyThread extends Thread {
        public MyThread(@NonNull String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
            }

        }
    }

    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;

    @Override
    public void onClick(View v) {
//        Toast.makeText(this, "dfasdf",Toast.LENGTH_LONG).show();

//        FeedBackWindowManager.getInstance().showWindow("dsfa");
//        startActivity(new Intent(this, PerformanceActivity.class));
//        MyThread myThread = new MyThread("testCpu");
//        myThread.start();

        if (v.getId() == R.id.linear_btn) {
            try {
                Debug.startMethodTracing(Environment.getExternalStorageDirectory() + "/test/testCpuPro.trace");
                A();
                Debug.stopMethodTracing();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("a=" + a + ",b=" + b + ",c=" + c + ",d=" + d);
        }

    }

    private void A() throws Exception {
        a++;
        B();
        D();
        Thread.sleep(100);
    }

    private void B() throws Exception {
        b++;
        C();
        Thread.sleep(100);
    }

    private void C() throws Exception {
        c++;
        Thread.sleep(100);
    }

    private void D() throws Exception {
        d++;
        C();
        B();
        Thread.sleep(100);
    }


    class OnPressTouchListener implements View.OnTouchListener {
        private Drawable stateDrawable;
        private Drawable orginalDrawable;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    orginalDrawable = v.getBackground();
                    if (orginalDrawable != null) {
                        orginalDrawable.setColorFilter(Color.parseColor("#33F13232"), PorterDuff.Mode.SRC_ATOP);
                    } else {
                        stateDrawable = new ColorDrawable(Color.parseColor("#33F13232"));
                        v.setBackgroundDrawable(stateDrawable);
                    }
                    v.invalidate();
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (orginalDrawable != null) {
                        orginalDrawable.clearColorFilter();
                    } else {
                        v.setBackgroundDrawable(null);
                    }
                    v.invalidate();
                    break;
            }
            return false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("BarrageActivity onDestroy");
    }
}
