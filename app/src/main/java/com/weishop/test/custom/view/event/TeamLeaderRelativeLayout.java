
package com.weishop.test.custom.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

public class TeamLeaderRelativeLayout extends RelativeLayout {

    public TeamLeaderRelativeLayout(Context context) {
        super(context);
    }

    public TeamLeaderRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TeamLeaderRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "TeamLeaderRelativeLayout dispatchTouchEvent", null);
//        boolean b = super.dispatchTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "TeamLeaderRelativeLayout onInterceptTouchEvent", null);
        return  super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "TeamLeaderRelativeLayout onTouchEvent", null);
        return super.onTouchEvent(ev);
    }
}
