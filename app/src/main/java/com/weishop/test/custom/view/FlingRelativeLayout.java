
package com.weishop.test.custom.view;

import android.content.Context;
import androidx.core.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.weishop.test.util.TestUtils;


public class FlingRelativeLayout extends RelativeLayout {

    public FlingRelativeLayout(Context context) {
        super(context);
    }

    public FlingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked = MotionEventCompat.getActionMasked(ev);
        final int actionIndex = MotionEventCompat.getActionIndex(ev);
        float x = MotionEventCompat.getX(ev, actionIndex);
        float y = MotionEventCompat.getY(ev, actionIndex);
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        String strAction = null;
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                strAction = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                strAction = "ACTION_POINTER_DOWN";
                break;
            case MotionEvent.ACTION_POINTER_UP:
                strAction = "ACTION_POINTER_UP";
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
            System.out.println("FlingRelativeLayout dispatchTouchEvent action=" + strAction + ",x="
                    + x + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY + ",actionMasked="
                    + actionMasked + ",actionIndex=" + actionIndex);
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        int actionMasked = ev.getActionMasked();
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
            case MotionEvent.ACTION_CANCEL:
                strAction = "ACTION_CANCEL";
                break;
        }
        if (TestUtils.OPEN) {
            System.out.println("DragRelativeLayout onInterceptTouchEvent action=" + strAction + ",x="
                    + x + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int actionMasked = MotionEventCompat.getActionMasked(ev);
        final int actionIndex = MotionEventCompat.getActionIndex(ev);
        float x = MotionEventCompat.getX(ev, actionIndex);
        float y = MotionEventCompat.getY(ev, actionIndex);
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
            System.out.println("DragRelativeLayout onTouchEvent action=" + strAction + ",x=" + x
                    + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY);
        }
        return super.onTouchEvent(ev);
    }
}
