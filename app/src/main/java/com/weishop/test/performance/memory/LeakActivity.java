
package com.weishop.test.performance.memory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.lang.ref.WeakReference;

public class LeakActivity extends Activity implements View.OnClickListener {
    private static InnerClass inner;

//    private static Activity activity;

    private MyHandler handler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        this.findViewById(R.id.leak).setOnClickListener(this);
        SingleInstance.getInstance(this);

        TestUtils.getMemoryInfo(this);

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
        System.gc();
        Student student = new Student("dfsafd");

    }

    static class InnerClass {

    }

    void createInnerClass() {
        inner = new InnerClass();
    }

}
