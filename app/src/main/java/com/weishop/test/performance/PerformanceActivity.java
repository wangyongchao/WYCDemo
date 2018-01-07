
package com.weishop.test.performance;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.ListView;

import com.weishop.test.data.ListViewAdapter;
import com.weishop.test.R;

import java.util.ArrayList;
import java.util.List;

public class PerformanceActivity extends Activity implements View.OnClickListener {


    private ListView mListView;
    private ListViewAdapter mAdapter;
    private List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Debug.startMethodTracing();

        setContentView(R.layout.activity_performance);

        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);
        findViewById(R.id.start).setOnClickListener(this);

        initData();

        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();


    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item" + i);
        }

    }

    @Override
    public void onClick(View v) {
        Thread thread = new Thread(new R1());
        thread.start();


    }

    class R1 implements Runnable {

        @Override
        public void run() {
            try {
                synchronized (mAdapter) {
                    mAdapter.wait(2000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }
}
