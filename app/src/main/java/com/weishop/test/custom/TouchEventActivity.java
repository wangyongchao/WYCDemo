
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

public class TouchEventActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);
        View innerView = findViewById(R.id.inner_view);
//        innerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtils.d("ProgramerButton onClick");
//
//            }
//        });

//        innerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//               TestUtils.printEvent(event,"ProgramerButton onTouch",null);
//                return false;
//            }
//        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        TestUtils.printEvent(ev, "TouchEventActivity dispatchTouchEvent", null);

        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TestUtils.printEvent(event, "TouchEventActivity onTouchEvent", null);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {


    }


}
