
package com.weishop.test.custom.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

public class ProgramerButton extends View {

    public ProgramerButton(Context context) {
        super(context);
    }

    public ProgramerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgramerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "ProgramerButton dispatchTouchEvent", null);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "ProgramerButton onTouchEvent", null);
        return super.onTouchEvent(ev);
    }

}
