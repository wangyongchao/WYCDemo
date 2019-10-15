
package com.weishop.test.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;

public class ActionBarActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
