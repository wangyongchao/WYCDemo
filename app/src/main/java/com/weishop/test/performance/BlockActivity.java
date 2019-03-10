
package com.weishop.test.performance;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

public class BlockActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    String action = "com.weishop.test.performance.BlockActivity";

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
            sendBroadcast(new Intent(action));

        }


    }

    class MyBroadCastReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                System.out.println("MyBroadCastReciver");
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
