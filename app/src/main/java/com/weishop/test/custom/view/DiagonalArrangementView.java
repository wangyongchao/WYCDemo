package com.weishop.test.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

/**
 * 对角线排列的view
 */
public class DiagonalArrangementView extends ViewGroup {
    private int viewHeight;
    private int viewWidth;
    int screenHeight;
    int screenWidth;

    public DiagonalArrangementView(Context context) {
        this(context, null);
    }

    public DiagonalArrangementView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiagonalArrangementView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewWidth = (int) context.getResources().getDimension(R.dimen.view_width);
        viewHeight = (int) context.getResources().getDimension(R.dimen.view_height);
        screenHeight = TestUtils.getScreenHeight(context);
        screenWidth = TestUtils.getScreenWidth(context);
        System.out.println("screenWidth=" + screenWidth + ",screenHeight=" + screenHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int maxHeight = 0;
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, viewWidth);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, viewHeight);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            maxHeight += child.getMeasuredHeight();
            maxWidth += child.getMeasuredHeight();
        }
        System.out.println("maxWidth=" + maxWidth + ",maxHeight=" + maxHeight);
        setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec), resolveSize(maxHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int childLeft = 0;
        int childTop = 0;
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            maxWidth += child.getMeasuredWidth();
            if (maxWidth > getWidth()) {
                return;
            }
            child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
            childLeft += child.getMeasuredWidth();
            childTop += child.getMeasuredHeight();
        }

    }
}
