
package com.weishop.test.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.weishop.test.util.LogUtils;


/**
 *
 */
public class CustomScrollView extends ScrollView {


    public CustomScrollView(Context context) {
        super(context);
        LogUtils.d("CustomScrollView");
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtils.d("CustomScrollView 2 Aruguments");
        int attributeCount = attrs.getAttributeCount();
        for (int i=0;i<attributeCount;i++){
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            LogUtils.d("attributeName="+attributeName+",attributeValue="+attributeValue);
        }
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtils.d("CustomScrollView 3 Aruguments");
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr,
                            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LogUtils.d("CustomScrollView 4 Aruguments");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }
}
