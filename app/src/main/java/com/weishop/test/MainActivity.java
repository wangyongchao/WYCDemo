
package com.weishop.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.weishop.test.util.TestUtils;

import java.util.ArrayList;
import java.util.List;

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

//        int has = ContextCompat.checkSelfPermission(this, Manifest.permission
//        .SYSTEM_ALERT_WINDOW);
//        if(has== PackageManager.PERMISSION_GRANTED){
//            System.out.println("PERMISSION_GRANTED");
//        }else if(has==PackageManager.PERMISSION_DENIED){
//            System.out.println("PERMISSION_DENIED");
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
//            .SYSTEM_ALERT_WINDOW}, 12345);
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

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(MainActivity.this);

            }
        }, 10 * 1000);


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

    private Bitmap createMsgBitmap(int width, int height, String msg, int textSize, int textColor) {
        Bitmap resultBitmap = null;
        if (TextUtils.isEmpty(msg)) {
            return resultBitmap;
        }
        if (msg.length() > 5) {
            msg = msg.substring(0, 5) + "...";
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        int textWidth = (int) paint.measureText(msg);
        if (textWidth > width) {
            width = textWidth;
        }

        Paint.FontMetrics fm = paint.getFontMetrics();
        int textHeight = (int) (fm.descent - fm.ascent);
        if (textHeight > height) {
            height = textHeight;
        }
        System.out.println("textwidth=" + textWidth + ",width=" + width);

        resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);
        Rect fontRect = new Rect();
        paint.getTextBounds(msg, 0, msg.length(), fontRect);
        Rect drawRect = new Rect(0, 0, width, height - textHeight / 2);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int baseLine =
                (drawRect.bottom + drawRect.bottom - fontMetricsInt.bottom - fontMetricsInt.top) / 2;
        canvas.drawText(msg, 0, baseLine, paint);
        return resultBitmap;
    }


    private Bitmap createMsgBitmap2(int width, int height, String msg, int textSize,
                                    int textColor) {
        Bitmap resultBitmap = null;
        if (TextUtils.isEmpty(msg)) {
            return resultBitmap;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);

        float textwidth = paint.measureText(msg);
        Paint.FontMetrics fm = paint.getFontMetrics();
        //文字基准线的下部距离-文字基准线的上部距离 = 文字高度
        float textheight = fm.descent - fm.ascent;


        Rect fontRect = new Rect();
        paint.getTextBounds(msg, 0, msg.length(), fontRect);

        resultBitmap = Bitmap.createBitmap((int) textwidth,
                (int) textheight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);

        Rect drawRect = new Rect(0, 0, fontRect.right, fontRect.bottom);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int baseLine =
                (drawRect.bottom + drawRect.bottom - fontMetricsInt.bottom - fontMetricsInt.top) / 2;
        canvas.drawText(msg, 0, baseLine, paint);
        return resultBitmap;
    }

    private List<String> getStrList(String text, int length) {
        int size = text.length() / length;
        if (text.length() % length != 0) {
            size += 1;
        }
        return getStrList(text, length, size);
    }

    private List<String> getStrList(String text, int length, int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(text, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    private String substring(String text, int f, int t) {
        if (f > text.length()) {
            return null;
        }
        if (t > text.length()) {
            return text.substring(f, text.length());
        } else {
            return text.substring(f, t);
        }
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
        int whitec = getResources().getColor(android.R.color.white);
        int nameTextSize =
                (int) getResources().getDimension(R.dimen.group_3v3_ceremony_name_size);
        Bitmap bitmap = createMsgBitmap(129, 22, "王永朝名字大于", nameTextSize, whitec);
        imageView.setImageBitmap(bitmap);
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
                        orginalDrawable.setColorFilter(Color.parseColor("#33F13232"),
                                PorterDuff.Mode.SRC_ATOP);
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
