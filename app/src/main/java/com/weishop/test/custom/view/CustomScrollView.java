
package com.weishop.test.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

import com.weishop.test.util.TestUtils;

import java.util.HashMap;


/**
 *
 */
public class CustomScrollView extends ScrollView {

    private ListView listView;
    private float mLastY;

    public CustomScrollView(Context context) {
        this(context, null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr,
                            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "onInterceptTouchEvent", null);
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "dispatchTouchEvent", null);
        return super.dispatchTouchEvent(ev);
    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean intercept = false;
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_MOVE:
//                if ((listView.getFirstVisiblePosition() == 0) && (ev.getY() > mLastY)) {
//                    intercept = true;
//                } else if (listView.getLastVisiblePosition() == listView.getCount() - 1 && (ev
//                        .getY() - mLastY) < 0) {
//                    intercept = true;
//                } else {
//                    intercept = false;
//                }
//                break;
//            default:
//                intercept = false;
//                break;
//
//        }
//        mLastY = ev.getY();
//        HashMap<String, String> map = new HashMap<>();
//        map.put("intercept", intercept + "");
//        TestUtils.printEvent(ev, "onInterceptTouchEvent", map);
//        return false;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "onTouchEvent", null);
        return super.onTouchEvent(ev);
    }

    public void setListView(ListView mListView) {
        this.listView = mListView;
    }

}
