
package com.weishop.test.custom.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

public class MyFrameLayout extends FrameLayout {

    public MyFrameLayout(Context context) {
        super(context);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        TestUtils.printMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
