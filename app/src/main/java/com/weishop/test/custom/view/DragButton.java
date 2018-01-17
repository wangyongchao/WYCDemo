
package com.weishop.test.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.weishop.test.util.TestUtils;

/**
 * Created by wangyongchao on 15/11/2.
 */
public class DragButton extends Button {

    public DragButton(Context context) {
        super(context);
    }

    public DragButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        int action = ev.getAction();
        String strAction = null;
        switch (action) {
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
            System.out.println("DragButton dispatchTouchEvent action=" + strAction + ",x=" + x
                    + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY + ",getLeft=" + getLeft()
                    + ",getTop=" + getTop() + ",getRight=" + getRight() + ",getbottom="
                    + getBottom());
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        int action = ev.getAction();
        String strAction = null;
        switch (action) {
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
            System.out.println("DragButton onTouchEvent action=" + strAction + ",x=" + x + ",y=" + y
                    + ",rawX=" + rawX + ",rawY=" + rawY);
        }

        return super.onTouchEvent(ev);
    }

}
