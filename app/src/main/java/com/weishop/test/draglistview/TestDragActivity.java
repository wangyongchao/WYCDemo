
package com.weishop.test.draglistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.draglistview.lib.DragSortController;
import com.weishop.test.draglistview.lib.DragSortListView;
import com.weishop.test.util.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDragActivity extends Activity implements View.OnClickListener, AbsListView.OnScrollListener {

    public int dragStartMode = DragSortController.ON_DOWN;
    public int removeMode = DragSortController.FLING_REMOVE;
    private DragSortListView mDslv;
    private DragSortController mController;


    private String[] array;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdrag);

        GestureDetector gestureDetector = new GestureDetector(this, new MyGestureDetectorListener());
        TextView textView = (TextView) this.findViewById(R.id.textview);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TestUtils.printEvent(event, "textView onTouch", null);
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });


        mDslv = (DragSortListView) findViewById(R.id.list);


        mController = buildController(mDslv);
        mDslv.setFloatViewManager(mController);
        mDslv.setOnTouchListener(mController);
        mDslv.setDragEnabled(true);
//        mDslv.setOnScrollListener(this);

        mDslv.setDropListener(onDrop);
        mDslv.setRemoveListener(onRemove);

        addHeader(this, mDslv);
        addFooter(this, mDslv);

        setListAdapter();


        mDslv.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(TestDragActivity.this);
            }
        }, 200);
    }

    public static void addHeader(Activity activity, DragSortListView dslv) {
        LayoutInflater inflater = activity.getLayoutInflater();
        int count = dslv.getHeaderViewsCount();

        TextView header = (TextView) inflater.inflate(R.layout.header_footer, null);
        header.setText("Header #" + (count + 1));

        dslv.addHeaderView(header, null, false);
    }

    public static void addFooter(Activity activity, DragSortListView dslv) {
        LayoutInflater inflater = activity.getLayoutInflater();
        int count = dslv.getFooterViewsCount();

        TextView footer = (TextView) inflater.inflate(R.layout.header_footer, null);
        footer.setText("Footer #" + (count + 1));

        dslv.addFooterView(footer, null, false);
    }

    public void setListAdapter() {
        array = getResources().getStringArray(R.array.names);
        list = new ArrayList<String>(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(this, R.layout.list_item_handle_left, R.id.text, list);
        mDslv.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
    }

    public DragSortController buildController(DragSortListView dslv) {
        // defaults are
        //   dragStartMode = onDown
        //   removeMode = flingRight
        DragSortController controller = new DragSortController(dslv);
        controller.setDragHandleId(R.id.drag_handle);
        controller.setClickRemoveId(R.id.click_remove);
        controller.setRemoveEnabled(true);
        controller.setSortEnabled(true);
        controller.setDragInitMode(dragStartMode);
        controller.setRemoveMode(removeMode);
        return controller;
    }

    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {
                    if (from != to) {
                        String item = adapter.getItem(from);
                        adapter.remove(item);
                        adapter.insert(item, to);
                        adapter.notifyDataSetChanged();
                    }
                }
            };

    private DragSortListView.RemoveListener onRemove =
            new DragSortListView.RemoveListener() {
                @Override
                public void remove(int which) {
                    adapter.remove(adapter.getItem(which));
                    adapter.notifyDataSetChanged();
                }
            };

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        System.out.println("onScrollStateChanged scrollState=" + scrollState);

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        System.out.println("onScroll firstVisibleItem=" + firstVisibleItem + ",visibleItemCount=" + visibleItemCount + ",totalItemCount=" + totalItemCount);
    }
}
