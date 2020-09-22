
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

public class ProcessViewActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_process_view);
        TestUtils.getProperty(this);

    }


    @Override
    public void onClick(View v) {

    }


}
