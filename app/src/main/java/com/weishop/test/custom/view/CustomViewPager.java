package com.weishop.test.custom.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.weishop.test.util.TestUtils;

/**
 * Created by wangyongchao on 2018/1/21.
 */

public class CustomViewPager extends ViewGroup {
    private int mLastX;
    private int scaledTouchSlop;
    private boolean isBeingFiling = false;
    private Scroller scroller;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomViewPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        scaledTouchSlop = ViewConfiguration.get(this.getContext()).getScaledTouchSlop();
        scroller = new Scroller(this.getContext());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        System.out.println("widthMode=" + TestUtils.getStringMeasureMod(widthMode) + ",widthSize=" + widthSize
                + ",heightMode=" + TestUtils.getStringMeasureMod(heightMode) + ",heightSize=" + heightSize);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("changed=" + changed + ",l=" + l + ",t=" + t + ",r=" + r + ",b=" + b);
        int childCount = getChildCount();
        System.out.println("childCount=" + childCount);
        int width = r - l;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            System.out.println("measuredWidth=" + measuredWidth);
            int left = i * width;
            int right = left + child.getMeasuredWidth();
            child.layout(left, 0, right, child.getMeasuredHeight());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int actionMasked = MotionEventCompat.getActionMasked(ev);
        int x = (int) MotionEventCompat.getX(ev, MotionEventCompat.getActionIndex(ev));
        TestUtils.printEvent(ev,"CustomViewPager onInterceptTouchEvent",null);
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mLastX - x) > scaledTouchSlop) {
                    isBeingFiling = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isBeingFiling = false;
                break;

        }
        return isBeingFiling;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = MotionEventCompat.getActionMasked(event);
        int x = (int) MotionEventCompat.getX(event, MotionEventCompat.getActionIndex(event));
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;

                break;
            case MotionEvent.ACTION_MOVE:
                int deltX = mLastX - x;
                scrollBy(deltX, 0);


                break;
            case MotionEvent.ACTION_UP:
                break;

        }

        return true;
    }
}
