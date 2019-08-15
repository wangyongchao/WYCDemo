
package com.weishop.test.list.draglistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.weishop.test.R;
import com.weishop.test.list.ListData;
import com.weishop.test.util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ListviewActivity extends Activity implements View.OnClickListener, AbsListView.OnScrollListener {

    private ListView mListView;
    private ListViewAdapter mAdapter;
    private List<ListData> mData = new ArrayList<ListData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                System.out.println("onScrollStateChanged scrollState=" + scrollState);

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                System.out.println("onScroll firstVisibleItem=" + firstVisibleItem +
                        ",visibleItemCount=" + visibleItemCount + ",totalItemCount=" + totalItemCount);
            }
        });

        findViewById(R.id.start).setOnClickListener(this);

        initData();


    }

    private void initData() {
        for (int i = 0; i < TestUtils.imageUrls.length; i++) {
            ListData listData = new ListData("item" + i, TestUtils.imageUrls[i]);
            mData.add(listData);
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
