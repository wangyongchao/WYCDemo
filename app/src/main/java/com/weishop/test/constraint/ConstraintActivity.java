
package com.weishop.test.constraint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;

public class ConstraintActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_constraint_sample);

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
