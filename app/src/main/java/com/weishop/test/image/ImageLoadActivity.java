
package com.weishop.test.image;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;

public class ImageLoadActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
