
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;


public class DragActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // // 设置无标题
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // // 设置全屏
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_drag);

        View btn = findViewById(R.id.btn);

        btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(DragActivity.this);
            }
        }, 200);

    }


}
