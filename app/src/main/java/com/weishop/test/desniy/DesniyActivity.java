
package com.weishop.test.desniy;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

public class DesniyActivity extends Activity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desniy);

        imageView = (ImageView) findViewById(R.id.imagetest);

        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(DesniyActivity.this);
                Drawable drawable = imageView.getDrawable();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                int intrinsicWidth = drawable.getIntrinsicWidth();
                System.out.println("intrinsicHeight="+intrinsicHeight+",intrinsicWidth="+intrinsicWidth);

            }
        }, 2000);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
