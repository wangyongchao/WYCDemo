
package com.weishop.test.performance.thin;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.weishop.test.R;

public class ThinActivity extends Activity implements View.OnClickListener {
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thin);
        findViewById(R.id.start).setOnClickListener(this);
        imageView = this.findViewById(R.id.thin_view);

    }

    @Override
    public void onClick(View v) {
        imageView.setImageResource(R.drawable.app_to_customer_placeholder);



    }
}
