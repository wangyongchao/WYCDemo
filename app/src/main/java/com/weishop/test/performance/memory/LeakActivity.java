
package com.weishop.test.performance.memory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.AppUtils;

import java.lang.ref.WeakReference;

public class LeakActivity extends Activity implements View.OnClickListener {


    private byte[] a = new byte[30 * AppUtils._1MB];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("wyc", "run");
                    Thread.sleep(100 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    static class MyHandler extends Handler {
        WeakReference<LeakActivity> weakReference;


        public MyHandler(LeakActivity activity) {
            weakReference = new WeakReference<LeakActivity>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() == null) {
                return;
            }

            LeakActivity leakActivity = weakReference.get();
        }
    }


    @Override
    public void onClick(View v) {

    }

    class InnerClass {
        public void inner() {

        }

    }

}
