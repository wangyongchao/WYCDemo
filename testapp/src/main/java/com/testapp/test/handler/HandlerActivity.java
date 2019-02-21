
package com.testapp.test.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.testapp.test.R;


public class HandlerActivity extends Activity implements View.OnClickListener {

    private HandlerThread handlerThread;
    private Handler workHandler;
    private TextView contentView;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);
        this.findViewById(R.id.start_main_thread).setOnClickListener(this);
        this.findViewById(R.id.start_main_thread_delay).setOnClickListener(this);
        this.findViewById(R.id.stop_main_thread).setOnClickListener(this);
        this.findViewById(R.id.start_work_thread).setOnClickListener(this);
        this.findViewById(R.id.stop_work_thread).setOnClickListener(this);
        contentView = (TextView) findViewById(R.id.contentView);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("handleMessage" + Thread.currentThread().getName());
            }
        };

        initWorkThreadHandler();


    }

    private void initWorkThreadHandler() {
        handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();

        workHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("workThead" + Thread.currentThread().getName() + ",");

            }
        };
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.start_main_thread:
                mHandler.sendMessage(Message.obtain());
                break;
            case R.id.start_main_thread_delay:
                Message message = new Message();
                message.what = 2;
                mHandler.sendMessageDelayed(message, 1000);
                break;
            case R.id.stop_main_thread:
                mHandler.removeMessages(2);
                break;
            case R.id.start_work_thread:
                workHandler.sendEmptyMessageDelayed(3,1000);
                break;
            case R.id.stop_work_thread:
                handlerThread.quitSafely();
                break;

        }

    }


}
