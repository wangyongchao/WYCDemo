
package com.weishop.test.performance;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.data.ListViewAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BlockActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    String action = "com.weishop.test.performance.BlockActivity";
    private Object lock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        findViewById(R.id.block_start1).setOnClickListener(this);
        findViewById(R.id.block_start2).setOnClickListener(this);
        textView = findViewById(R.id.text);
        IntentFilter filter = new IntentFilter(action);
        this.registerReceiver(new MyBroadCastReciver(), filter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.block_start1) {
            try {
                Thread.sleep(10000);
                System.out.println("fadsfd");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
//            sendBroadcast(new Intent(action));

            new LockTask().execute("");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                System.out.println("onClick");
                // The main thread requires lockedResource here
                // but it has to wait until LockTask finishes using it.
            }
        }


    }

    public class LockTask extends AsyncTask<String, Integer, Long> {
        @Override
        protected Long doInBackground(String... params) {
            synchronized (lock) {
                // This is a long-running operation, which makes
                // the lock last for a long time
                boolean is=true;
                System.out.println("doInBackground");
                while (is){

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
                Thread.sleep(15000);
                System.out.println("MyBroadCastReciver after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
