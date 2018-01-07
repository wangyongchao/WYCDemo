
package com.weishop.test.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eventbus);
        findViewById(R.id.get).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.content);

        EventBus.getDefault().register(this);

    }


    @Override
    public void onClick(View v) {
        MessageEvent event = new MessageEvent("zhangsan", "1234");
        EventBus.getDefault().post(event);
        System.out.println("onClick");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void helloEventBus(MessageEvent event) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread=" + Thread.currentThread().getName() + "," + event.name);
    }
}
