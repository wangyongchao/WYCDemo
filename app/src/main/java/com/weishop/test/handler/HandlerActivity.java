
package com.weishop.test.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.weishop.test.R;


public class HandlerActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);
        this.findViewById(R.id.handler).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        mHandler.sendMessage(Message.obtain());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.sendMessage(Message.obtain());

            }
        }).start();
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("handleMessage");
        }
    };


}
