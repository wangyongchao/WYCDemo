
package com.weishop.test.strictmode;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.SystemClock;
import android.view.View;

import com.weishop.test.data.Point;
import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StrictModeActivity extends Activity implements View.OnClickListener {

    public static boolean DEBUG_STRICT_MODE = true;
    ArrayList<Point> points;

    private MyReciever reciever ;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

        if (DEBUG_STRICT_MODE) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork()   // or .detectAll() for all detectable problems
//                    .detectCustomSlowCalls()
//                    .penaltyLog()
//                    .penaltyDialog()//触发违规时候，弹出对话框
//                    .penaltyDeath()//触发违规时候，crash掉应用
//                    .penaltyDropBox()
//                    .build());


            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectLeakedSqlLiteObjects()
//                    .detectLeakedClosableObjects()
//                    .detectActivityLeaks()
//                    .detectLeakedClosableObjects()
                    .detectLeakedRegistrationObjects()
                    .setClassInstanceLimit(Point.class, 2)
                    .penaltyLog()
//                    .penaltyDeath()
                    .build());
        }

        setContentView(R.layout.activity_strict);
        View view = findViewById(R.id.operate);
        view.setOnClickListener(this);

        reciever= new MyReciever();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.aa.bb");
        registerReceiver(reciever, filter);


    }

    class MyReciever extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }


    @Override
    public void onClick(View v) {
//        postNetwork();
//        writeToExternalStorage();
//        customSlowCall();
//        noCloseStream();

        testInstance();

    }

    public void testInstance() {
       points = new ArrayList<>();
        Point point = new Point(2, 4);
        points.add(point);
        point = new Point(3, 4);
        points.add(point);
    }


    /**
     * 检测内存泄漏
     */
    public void testLeak() {
        TestUtils.contexts.add(this);
    }


    /**
     * 检测没有关闭流
     */
    public void noCloseStream() {
        System.out.println("noCloseStream");
        File newxmlfile = new File(Environment.getExternalStorageDirectory(), "castiel.txt");
        try {
            newxmlfile.createNewFile();
            FileWriter fw = new FileWriter(newxmlfile);
            fw.write("猴子搬来的救兵WooYun");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义耗时操作
     */
    private void customSlowCall() {
        try {
            long startTime = SystemClock.uptimeMillis();
            Thread.sleep(1000);
            long cost = SystemClock.uptimeMillis() - startTime;
            if (cost > 200) {
                StrictMode.noteSlowCall("slowCall cost=" + cost);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 网络连接的操作
     */
    private void postNetwork() {
        try {
            URL url = new URL("http://www.wooyun.org");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines = null;
            StringBuffer sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读写磁盘
     */
    public void writeToExternalStorage() {
        File externalStorage = Environment.getExternalStorageDirectory();
        File destFile = new File(externalStorage, "dest.txt");
        try {
            OutputStream output = new FileOutputStream(destFile, true);
            output.write("robert.com".getBytes());
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }
}
