
package com.weishop.test.animator;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;


/**
 *
 */
public class ChangeOrderLayout extends RelativeLayout {
    private Context mContext;
    private LayoutInflater inflater;
    private LayoutTransition mLayoutTransition;
    private int itemInterval;

    public ChangeOrderLayout(Context context) {
        this(context, null);
    }

    public ChangeOrderLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ChangeOrderLayout(@NonNull Context context, @Nullable AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        inflater = LayoutInflater.from(mContext);
        itemInterval = TestUtils.dip2px(this.getContext(), 8);
        setChildrenDrawingOrderEnabled(true);

    }


    /**
     * 插入一个元素
     */
    public void insertElement(String key, int number) {
        View view = inflater.inflate(R.layout.view_liancheng_item, null);
        TextView keyTextView = view.findViewById(R.id.item_key);
        TextView numTextView = view.findViewById(R.id.item_number);
        keyTextView.setText(String.valueOf(key));
        numTextView.setText("x" + number);
        LayoutParams layoutParams =
                new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        if (getChildCount() != 0) {
            //根据top margin控制在屏幕上的显示位置
            layoutParams.topMargin = getChildCount() * (itemInterval);
        }
        addView(view, layoutParams);

    }

    @Override
    protected int getChildDrawingOrder(int childCount, int drawingPosition) {
        int childDrawingOrder = super.getChildDrawingOrder(childCount, drawingPosition);
        LogUtils.d("getChildDrawingOrder drawingPosition=" + drawingPosition
                + ",childDrawingOrder=" + childDrawingOrder);

        return childCount-1-drawingPosition;
    }
}
