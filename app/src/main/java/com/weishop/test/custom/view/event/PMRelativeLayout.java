
package com.weishop.test.custom.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.weishop.test.util.TestUtils;

public class PMRelativeLayout extends RelativeLayout {

    public PMRelativeLayout(Context context) {
        super(context);
    }

    public PMRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PMRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "PMRelativeLayout dispatchTouchEvent", null);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "PMRelativeLayout onInterceptTouchEvent", null);

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "PMRelativeLayout onTouchEvent", null);
        return super.onTouchEvent(ev);
    }
}
