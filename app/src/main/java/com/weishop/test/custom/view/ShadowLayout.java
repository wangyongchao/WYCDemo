
package com.weishop.test.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import static android.R.attr.path;


/**
 * 对角线排列
 */
public class ShadowLayout extends FrameLayout {


    private Paint paint;
    private Path path;

    public ShadowLayout(Context context) {
        this(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.shadow20));// 设置红色
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        path.lineTo(getMeasuredWidth() / 2, 0); //左顶点
        path.lineTo(getMeasuredWidth() / 3, getMeasuredHeight()); //右底部
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        path.lineTo(0, getMeasuredHeight()); // 左底部
        path.close();

//        path.lineTo(0,getMeasuredWidth()/2);

        canvas.drawPath(path, paint);


    }
}
