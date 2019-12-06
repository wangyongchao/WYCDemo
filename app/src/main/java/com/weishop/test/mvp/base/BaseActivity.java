
package com.weishop.test.mvp.base;

import android.app.Activity;
import android.os.Bundle;

import com.weishop.test.util.LogUtils;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("BaseActivity onCreate");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("BaseActivity onDestroy");
    }


}
