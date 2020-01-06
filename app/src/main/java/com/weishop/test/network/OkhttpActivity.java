
package com.weishop.test.network;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;

import java.io.IOException;

public class OkhttpActivity extends Activity implements View.OnClickListener {

    private TestInterceptorsOkhttp testOkhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        testOkhttp = new TestInterceptorsOkhttp(this);
        this.findViewById(R.id.send).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        try {
            testOkhttp.testAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
