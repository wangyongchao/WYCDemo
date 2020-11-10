
package com.weishop.test.list.recycler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.data.MyAdapter;
import com.weishop.test.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity implements View.OnClickListener {
    private List<String> mData = new ArrayList<String>();

    private RecyclerView mRecyclerView;

    private MyAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    private int minLeftItemCount = 10;//剩余多少条时开始加载更多


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("RecyclerViewActivity onCreate");

        setContentView(R.layout.activity_recyclerview);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);// listview功能
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(defaultItemAnimator);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(mLayoutManager);//gridview 功能


//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
//                StaggeredGridLayoutManager.HORIZONTAL));

        initData();

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int itemCount = layoutManager.getItemCount();
//                int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
//                LogUtils.d("【总数】" + itemCount + "【位置】" + lastPosition);
//                if (lastPosition == layoutManager.getItemCount() - 1) {//容错处理，保证滑到最后一条时一定可以加载更多
//                    onLoadMore();
//                } else {
//                    if (itemCount > minLeftItemCount) {
//                        if (lastPosition == itemCount - minLeftItemCount) {
//                            //一定要意识到，onScrolled方法并不是一直被回调的，估计最多一秒钟几十次
//                            //所以当此条件满足时，可能并没有回调onScrolled方法，也就不会调用onLoadMore方法
//                            //所以一定要想办法弥补这隐藏的bug，最简单的方式就是当滑到最后一条时一定可以加载更多
//                            onLoadMore();
//                        }
//                    } else {//（第一次进入时）如果总数特别少，直接加载更多
//                        onLoadMore();
//                    }
//                }
//            }
//
//        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                String state = "";
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //RecyclerView 停止滚动的时候调用
                    state = "SCROLL_STATE_IDLE";
                    int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                    for (int i = firstVisibleItemPosition; i < lastVisibleItemPosition; i++) {
                        View view = mLayoutManager.findViewByPosition(i);
                        Rect rect = new Rect();
                        view.getGlobalVisibleRect(rect);
                        LogUtils.d("i=" + i +",rect = " + rect);
                    }


                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //正在通过手势滚动
                    state = "SCROLL_STATE_DRAGGING";

                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //滑动后手拿开，通过惯性滚动
                    state = "SCROLL_STATE_SETTLING";

                }

            }

            /**
             *
             * @param recyclerView
             * @param dx 水平方向每次滚动的距离
             * @param dy 垂直方向每次滚动的距离
             */
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                LogUtils.d("dx=" + dx + ",dy=" + dy);
                //第一个可见的item在列表中的位置，以0开始
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();


                //第一个完全可见的item在列表中的位置
                int firstCompletelyVisibleItemPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();

                //最后一个可见的item在列表中的位置,可以有遮盖，不是完全可见
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();

                //最后一个完全可见的item在列表中的位置,整个item都在屏幕中
                int lastCompletelyVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
//                LogUtils.d("firstVisibleItemPosition="+firstVisibleItemPosition
//                        +",firstCompletelyVisibleItemPosition="+firstCompletelyVisibleItemPosition
//                        +",lastVisibleItemPosition="+lastVisibleItemPosition
//                        +",lastCompletelyVisibleItemPosition="+lastCompletelyVisibleItemPosition);

            }
        });

        findViewById(R.id.delete).setOnClickListener(this);

        findViewById(R.id.add).setOnClickListener(this);

    }

    private void onLoadMore() {
        LogUtils.d("onLoadMore");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item" + i);
        }

    }

    @Override
    public void onClick(View v) {
        if (R.id.add == v.getId()) {
            mAdapter.addData(mAdapter.getItemCount(), "new item");

        } else {
//            mAdapter.removeData(mAdapter.getItemCount()-1);
            mAdapter.notifyItemMoved(0, 3);
        }

    }
}
