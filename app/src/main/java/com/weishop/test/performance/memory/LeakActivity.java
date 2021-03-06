
package com.weishop.test.performance.memory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.weishop.test.R;

public class LeakActivity extends Activity implements View.OnClickListener {

    private MyHandler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        findViewById(R.id.leak).setOnClickListener(this);
        myHandler = new MyHandler();

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }


    @Override
    public void onClick(View v) {
        myHandler.sendEmptyMessageDelayed(100, 100000);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        myHandler.removeMessages(100);
    }
}
