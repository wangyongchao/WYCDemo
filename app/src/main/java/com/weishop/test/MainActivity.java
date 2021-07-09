
package com.weishop.test;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.weishop.test.activitycharacter.BActivity;
import com.weishop.test.util.AutoPlayHelper;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

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
        AutoPlayHelper.getInstance();

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

    private void start() {
        LogUtils.d("start");
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

        testDate();


    }

    private void testDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        calendar.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(calendar.getTime());
        System.out.println(format);
    }


    private void test() {
        NetworkInterface printer = (NetworkInterface) new MyProxy(this).getProxy(NetworkInterface.class);
        try {
            printer.getHardwareAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        getNewMac();
    }

    /**
     * 通过网络接口取
     *
     * @return
     */
    private static String getNewMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String getDeviceMac(Context context) {
        String mMicAddress = "";
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wm != null) {
            mMicAddress = wm.getConnectionInfo().getMacAddress();
        }
        return mMicAddress;
    }

    public static String getDeviceMac1(Context context) {
        String mMicAddress = "";
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wm != null) {
            WifiInfo connectionInfo = wm.getConnectionInfo();
            LogUtils.d("connectionInfo=" + connectionInfo);
        }
        return mMicAddress;
    }

    private String getPhoneIMEI() {
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Service.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }


    public static String getDeviceMacHook(Context context) {
        String mMicAddress = "";
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        try {
            Field mService = WifiManager.class.getDeclaredField("mService");
            mService.setAccessible(true);
            Object object = mService.get(wm);
            Class aClass = Class.forName("android.net.wifi.IWifiManager");
            Object proxyInstance = Proxy.newProxyInstance(context.getClass().getClassLoader(), new
                    Class[]{aClass}, new InvocationHandler() {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 操作交由 sOriginService 处理，不拦截通知
                    String name = method.getName();
                    LogUtils.d("name=" + name);
                    Object invoke = method.invoke(object, args);
                    if ("getConnectionInfo".equals(name)) {
                        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                        String exceptionDetail = TestUtils.getExceptionDetail(stackTrace);
                        LogUtils.d(exceptionDetail);


                    }

                    return method.invoke(object, args);

                }
            });
            LogUtils.d("object=" + object + ",aClass=" + aClass);
            Field sServiceField = WifiManager.class.getDeclaredField("mService");
            sServiceField.setAccessible(true);
            sServiceField.set(wm, proxyInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mMicAddress;
    }

    private void hookWifiManager() {

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
