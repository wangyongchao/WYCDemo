
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weishop.test.R;

public class CustomActivity extends Activity implements View.OnClickListener {

    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom);

    }


    @Override
    public void onClick(View v) {

    }


}
