
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;

import com.weishop.test.R;


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

    }


}
