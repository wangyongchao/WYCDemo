
package com.weishop.test.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.weishop.test.util.TestUtils;


/**
 * 对角线排列
 */
public class DiagonalLineLayout extends ViewGroup {


    public DiagonalLineLayout(Context context) {
        this(context, null);
    }

    public DiagonalLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiagonalLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int withdmode = MeasureSpec.getMode(widthMeasureSpec);
        int withdsize = MeasureSpec.getSize(widthMeasureSpec);
        System.out.println("withdmode=" + TestUtils.getStringMeasureMod(withdmode) + ",withdsize=" + withdsize);

        int heightdmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        System.out.println("heightdmode=" + TestUtils.getStringMeasureMod(heightdmode) + ",heightsize=" + heightsize);

        measureChildren(widthMeasureSpec, heightMeasureSpec);


        setMeasuredDimension(resolveSize(withdsize, widthMeasureSpec), resolveSize(heightsize, heightMeasureSpec));

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childLeft = 0;
        int childTop = 0;
        int totalHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            if (childTop + measuredHeight > bottom) {
                break;
            }

            child.layout(childLeft, childTop, childLeft + measuredWidth, childTop + measuredHeight);

            totalHeight = totalHeight + measuredHeight;
            childLeft = (int) (totalHeight * getMeasuredWidth() / (float) getMeasuredHeight());
            childTop = childTop + measuredHeight;


        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
