
package com.weishop.test.performance.anr;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;

import java.util.concurrent.TimeUnit;

public class BlockActivity extends Activity implements View.OnClickListener {
    String action = "android.net.conn.CONNECTIVITY_CHANGE";
    private Object lock = new Object();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        findViewById(R.id.block_start1).setOnClickListener(this);
        findViewById(R.id.block_start2).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        blockByIO();
    }

    private void blockBroadCastReciver(){
        IntentFilter filter = new IntentFilter();
//        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
//        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(new MyBroadCastReciver(), filter);
    }


    /**
     * 主线程做耗时操作
     */
    private void blockByIO() {
        CalcMD5.getMD5();
    }


    /**
     * 等待子线程的锁
     */
    private void blockByLock() {
        //争夺资源造成ANR
        new LockTask().execute("");
        try {
            //等待子线程先获得锁
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            LogUtils.d("onClick");
        }
    }

    public class LockTask extends AsyncTask<String, Integer, Long> {
        @Override
        protected Long doInBackground(String... params) {
            synchronized (lock) {
                LogUtils.d("doInBackground");
                CalcMD5.getMD5();
                return 4l;
            }
        }
    }


    class MyBroadCastReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                LogUtils.d("MyBroadCastReciver befor");
                Thread.sleep(20000);
                LogUtils.d("MyBroadCastReciver after");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
