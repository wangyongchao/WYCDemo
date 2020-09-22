
package com.weishop.test.custom.view;

import android.content.Context;
import androidx.core.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.weishop.test.util.TestUtils;

public class MyRelativeLayout extends RelativeLayout {

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "dispatchTouchEvent", null);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        int action = ev.getAction();
        String strAction = null;
        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                strAction = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                strAction = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                strAction = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                strAction = "ACTION_CANCEL";
                break;
        }
        if (TestUtils.OPEN) {
            System.out.println("MyRelativeLayout onInterceptTouchEvent action=" + strAction + ",x="
                    + x + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY);
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int actionMasked = MotionEventCompat.getActionMasked(ev);
        final int actionIndex = MotionEventCompat.getActionIndex(ev);
        float x = MotionEventCompat.getX(ev, actionIndex);
        float y = MotionEventCompat.getY(ev, actionIndex);
        int pointerId = MotionEventCompat.getPointerId(ev, actionIndex);
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        String strAction = null;
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                strAction = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                strAction = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                strAction = "ACTION_UP";
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                strAction = "ACTION_POINTER_DOWN";
                break;
            case MotionEvent.ACTION_POINTER_UP:
                strAction = "ACTION_POINTER_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                strAction = "ACTION_CANCEL";
                break;
        }
        if (TestUtils.OPEN) {
            System.out.println("MyRelativeLayout dispatchTouchEvent action=" + strAction + ",x="
                    + x + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY + ",actionMasked="
                    + actionMasked + ",actionIndex=" + actionIndex + ",pointerId=" + pointerId);
        }

        return true;
    }
}
