
package com.weishop.test.draglistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.weishop.test.R;
import com.weishop.test.data.ListViewAdapter;
import com.weishop.test.draglistview.MyGestureDetectorListener;

import java.util.ArrayList;
import java.util.List;

public class ListviewActivity extends Activity implements View.OnClickListener,AbsListView.OnScrollListener{

    private ListView mListView;
    private ListViewAdapter mAdapter;
    private List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        GestureDetector gestureDetector = new GestureDetector(this, new MyGestureDetectorListener());


        mListView.setOnScrollListener(this);
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
        findViewById(R.id.start).setOnClickListener(this);

        initData();


    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item" + i);
        }

    }

    @Override
    public void onClick(View v) {
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
