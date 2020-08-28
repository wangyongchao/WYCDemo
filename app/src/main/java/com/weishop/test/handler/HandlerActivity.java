
package com.weishop.test.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;

import java.net.URL;


public class HandlerActivity extends Activity implements View.OnClickListener {

    private HandlerThread handlerThread;
    private Handler handler;
    private TextView contentView;
    private DownloadFilesTask downloadFilesTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);
        this.findViewById(R.id.handler).setOnClickListener(this);
        this.findViewById(R.id.stop).setOnClickListener(this);
        contentView = findViewById(R.id.contentView);

        handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtils.d(Thread.currentThread().getName() + ",");

            }
        };

        handlerThread.getLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                LogUtils.d("queueIdle");
                return false;
            }
        });

        downloadFilesTask = new DownloadFilesTask();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.handler) {



        } else {
            handler.sendEmptyMessage(0);
        }

    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("handleMessage");
        }
    };


}
