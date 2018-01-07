
package com.weishop.test.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.weishop.test.R;


/**
 * Created by wangyongchao on 15/11/2.
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.CustomizeStyle);
        // 第三个参数必须要在application中指定theme
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 1.
         * 直接在XML中定义>style定义>由defStyleAttr定义的值>defStyleRes指定的默认值、直接在Theme中指定的值
         * 2. defStyleAttr（即defStyle）不为0且在当前Theme中可以找到这个attribute的定义时，
         * defStyleRes不起作用
         * ，所以attr_four虽然在defStyleRes（DefaultCustomizeStyle）中定义了，但取到的值仍为null。
         */
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Customize, defStyleAttr,
                0);
        String one = a.getString(R.styleable.Customize_attr_one);
        String two = a.getString(R.styleable.Customize_attr_two);
        String three = a.getString(R.styleable.Customize_attr_three);
        String four = a.getString(R.styleable.Customize_attr_four);
        System.out.println("one=" + one + ",two=" + two + ",three=" + three + ",four=" + four);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("onMeasure widthMeasureSpec=" + widthMeasureSpec + ",heightMeasureSpec="
                + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("onLayout changed=" + changed + ",left=" + left + ",top=" + top
                + ",right=" + right + ",bottom=" + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("onDraw");
        super.onDraw(canvas);
    }
}
