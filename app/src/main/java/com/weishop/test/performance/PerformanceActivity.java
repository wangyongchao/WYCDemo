
package com.weishop.test.performance;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.weishop.test.list.ListData;
import com.weishop.test.list.draglistview.ListViewAdapter;
import com.weishop.test.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PerformanceActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    private ListView mListView;
    private ListViewAdapter mAdapter;
    private List<ListData> mData = new ArrayList<ListData>();

    static {
//        Debug.startMethodTracing("startTime");
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        this.findViewById(R.id.start).setOnClickListener(this);
        textView = findViewById(R.id.textview);
        mListView = findViewById(R.id.list);
        testRead();
        initData();

    }

    private void initData() {
        for (int i = 0; i < 1000; i++) {
        }
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        textView.setText("测试");
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();


    }


    private void testRead() {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory, "/test/testimage.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = fileInputStream.read(bytes)) != -1) {
                System.out.println("lenght=" + read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void test() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Debug.stopMethodTracing();
    }
}
