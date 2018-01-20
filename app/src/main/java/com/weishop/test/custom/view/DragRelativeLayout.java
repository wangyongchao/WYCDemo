
package com.weishop.test.custom.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

import com.weishop.test.util.TestUtils;

import static android.support.v4.widget.ExploreByTouchHelper.INVALID_ID;

public class DragRelativeLayout extends RelativeLayout {
    private final Rect mTempRect = new Rect();
    private boolean touchPointerInView;
    private float mLastX;
    private float mLastY;
    private int mTouchSlop;

    private boolean mIsBeingDragged;
    private DragButton dragButton;

    private int activePointerIndex;
    private int mSecondaryPointerId;
    private float mSecondaryLastX;
    private float mSecondaryLastY;
    private int mActivePointerId;

    public DragRelativeLayout(Context context) {
        super(context);
    }

    public DragRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked = MotionEventCompat.getActionMasked(ev);
        final int actionIndex = MotionEventCompat.getActionIndex(ev);
        float x = MotionEventCompat.getX(ev, actionIndex);
        float y = MotionEventCompat.getY(ev, actionIndex);
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        int action = ev.getAction();
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
            System.out.println("DragRelativeLayout dispatchTouchEvent action=" + strAction + ",x="
                    + x + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY + ",actionMasked="
                    + actionMasked + ",actionIndex=" + actionIndex);
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean isTouchPointerInView(View view, float x, float y) {
        view.getHitRect(mTempRect);
        int xInt = (int) (x + getScrollX());
        int yInt = (int) (y + getScrollY());
        if (mTempRect.contains(xInt, yInt)) {
            return true;
        }
        return false;
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
                int actionIndex = MotionEventCompat.getActionIndex(ev);
                mActivePointerId =  MotionEventCompat.findPointerIndex(ev, actionIndex);
                dragButton = (DragButton) getChildAt(0);
                touchPointerInView = isTouchPointerInView(dragButton, x, y);
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                strAction = "ACTION_MOVE";
                int deltx = (int) (mLastX - x);
                int delty = (int) (mLastY - y);
                if ((Math.abs(deltx) > mTouchSlop || Math.abs(delty) > mTouchSlop) && touchPointerInView) {
                    System.out.println("mIsBeingDragged");
                    mIsBeingDragged = true;
                }

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
        return mIsBeingDragged;
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
                int deltx = (int) (mLastX - x);
                int delty = (int) (mLastY - y);
                if (mIsBeingDragged) {
                    scrollBy(deltx, delty);
                    mLastX = x;
                    mLastY = y;
                }
                if(mSecondaryPointerId!=INVALID_ID){
                    int mSecondaryPointerIndex = MotionEventCompat.findPointerIndex(ev, mSecondaryPointerId);
                    mSecondaryLastX = MotionEventCompat.getX(ev, mSecondaryPointerIndex);
                    mSecondaryLastY = MotionEventCompat.getY(ev, mSecondaryPointerIndex);
                }
                break;
            case MotionEvent.ACTION_UP:
                strAction = "ACTION_UP";
                mIsBeingDragged = false;
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                strAction = "ACTION_POINTER_DOWN";
                activePointerIndex = MotionEventCompat.getActionIndex(ev);
                mSecondaryPointerId = MotionEventCompat.findPointerIndex(ev, activePointerIndex);
                mSecondaryLastX = MotionEventCompat.getX(ev, activePointerIndex);
                mSecondaryLastY = MotionEventCompat.getY(ev, mActivePointerId);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                strAction = "ACTION_POINTER_UP";
                activePointerIndex = MotionEventCompat.getActionIndex(ev);
                int curPointerId = MotionEventCompat.getPointerId(ev, activePointerIndex);
                if (curPointerId == mActivePointerId) { // active pointer up
                    mActivePointerId = mSecondaryPointerId;
                    mLastX = mSecondaryLastX;
                    mLastY = mSecondaryLastY;
                    mSecondaryPointerId = INVALID_ID;
                    mSecondaryLastY = 0;
                    mSecondaryLastX = 0;
                    //重复代码，为了让逻辑看起来更加清晰
                } else { //如果是secondary pointer up
                    mSecondaryPointerId = INVALID_ID;
                    mSecondaryLastY = 0;
                    mSecondaryLastX = 0;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                strAction = "ACTION_CANCEL";
                break;
        }
        if (TestUtils.OPEN) {
            System.out.println("DragRelativeLayout onTouchEvent action=" + strAction + ",x=" + x
                    + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY);
        }

        return mIsBeingDragged;
    }
}
