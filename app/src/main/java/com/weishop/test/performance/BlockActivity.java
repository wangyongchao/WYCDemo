
package com.weishop.test.performance;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;

public class BlockActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    String action = "com.weishop.test.performance.BlockActivity";
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
        textView = findViewById(R.id.text);
        IntentFilter filter = new IntentFilter(action);
        this.registerReceiver(new MyBroadCastReciver(), filter);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(action));
            }
        }, 10000);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.block_start1) {
            try {
                Thread.sleep(20000);
                System.out.println("fadsfd");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            sendBroadcast(new Intent(action));


//争夺资源造成ANR
//            new LockTask().execute("");
//            try {
//                TimeUnit.MILLISECONDS.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            synchronized (lock) {
//                System.out.println("onClick");
//                // The main thread requires lockedResource here
//                // but it has to wait until LockTask finishes using it.
//            }
        }


    }

    public class LockTask extends AsyncTask<String, Integer, Long> {
        @Override
        protected Long doInBackground(String... params) {
            synchronized (lock) {
                // This is a long-running operation, which makes
                // the lock last for a long time
                boolean is = true;
                System.out.println("doInBackground");
                while (is) {

                }
                return 4l;
            }
        }
    }


    class MyBroadCastReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                System.out.println("MyBroadCastReciver befor");
//                Thread.sleep(10000);
                System.out.println("MyBroadCastReciver after");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
