
package com.weishop.test.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.weishop.test.util.TestUtils;

/**
 * Created by wangyongchao on 15/11/2.
 */
public class MyView extends View {

    private Paint paint;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setColor(Color.RED);// 设置红色
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        int action = ev.getAction();
        String strAction = null;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                strAction = "ACTION_DOWN";
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
            System.out.println("MyView dispatchTouchEvent action=" + strAction + ",x=" + x + ",y="
                    + y + ",rawX=" + rawX + ",rawY=" + rawY + ",getLeft=" + getLeft() + ",getTop="
                    + getTop() + ",getRight=" + getRight() + ",getbottom=" + getBottom());
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        int action = ev.getAction();
        String strAction = null;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                strAction = "ACTION_DOWN";
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
            System.out.println("MyView onTouchEvent action=" + strAction + ",x=" + x + ",y=" + y
                    + ",rawX=" + rawX + ",rawY=" + rawY);
        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path1 = new Path();
//        path1.moveTo(180, 200);
        path1.lineTo(100, 100);
        path1.lineTo(210, 210);
//        path1.close();//封闭
        canvas.drawPath(path1, paint);


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
