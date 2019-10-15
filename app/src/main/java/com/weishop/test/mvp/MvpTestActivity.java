
package com.weishop.test.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.weishop.test.R;
import com.weishop.test.list.ListData;
import com.weishop.test.list.draglistview.ListViewAdapter;
import com.weishop.test.mvp.base.BaseMvpActivity;

import java.util.List;

public class MvpTestActivity extends BaseMvpActivity<TestPresenter> implements View.OnClickListener,
        AbsListView.OnScrollListener, TestView {

    private ListView mListView;
    private ListViewAdapter mAdapter;


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
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                System.out.println("onScroll firstVisibleItem=" + firstVisibleItem +
                        ",visibleItemCount=" + visibleItemCount + ",totalItemCount=" + totalItemCount);
            }
        });

        findViewById(R.id.start).setOnClickListener(this);

        mPresenter.testData();


    }

    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void test(List<ListData> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }
}
