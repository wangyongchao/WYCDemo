package com.weishop.test.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

/**
 * 自动换行的view
 */
public class AutoLineGroup extends ViewGroup {
    private int viewHeight;
    private int viewWidth;
    int screenHeight;
    int screenWidth;

    public AutoLineGroup(Context context) {
        this(context, null);
    }

    public AutoLineGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLineGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewWidth = (int) context.getResources().getDimension(R.dimen.view_width);
        viewHeight = (int) context.getResources().getDimension(R.dimen.view_height);
        screenHeight = TestUtils.getScreenHeight(context);
        screenWidth = TestUtils.getScreenWidth(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        int lineMaxHeight = 0;

        int sumLineWidth = 0;
        int maxHeight = 0;
        int maxWidth = 0;
        boolean isAutoLine = false;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            layoutParams.width = viewWidth;
            layoutParams.height = viewHeight;
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            int childUsedWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            sumLineWidth += childUsedWidth;
            int lineHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (sumLineWidth > widthSize) {
                //需要自动换行了
                isAutoLine = true;
                maxHeight += lineMaxHeight;
                sumLineWidth = childUsedWidth;
                lineMaxHeight = lineHeight;
            } else {
                lineMaxHeight = Math.max(lineHeight, lineMaxHeight);
            }
        }
        if (isAutoLine) {
            maxWidth = widthSize;
            maxHeight += lineMaxHeight;
        } else {
            maxWidth = sumLineWidth;
            maxHeight = lineMaxHeight;
        }
        setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec), resolveSize(maxHeight, heightMeasureSpec));


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);



        }


    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(this.getContext(), attrs);
    }
}
