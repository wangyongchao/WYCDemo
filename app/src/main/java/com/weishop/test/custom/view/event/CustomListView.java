package com.weishop.test.custom.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.weishop.test.util.TestUtils;

public class CustomListView extends ListView {

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "CustomListView dispatchTouchEvent", null);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "CustomListView onTouchEvent", null);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "CustomListView onInterceptTouchEvent", null);
        // 判断是否滑动到顶部了

        return super.onInterceptTouchEvent(ev);
    }
}