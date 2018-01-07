
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.custom.view.AutoLineLayout;
import com.weishop.test.custom.view.DiagonalLineLayout;
import com.weishop.test.util.TestUtils;

import java.util.Random;

public class CustomActivity extends Activity implements View.OnClickListener {

    private LayoutInflater mInflater;
    private Random random = new Random(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shadow);
        View view = findViewById(R.id.button);
        view.setOnClickListener(this);
        mInflater = LayoutInflater.from(this);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(CustomActivity.this);
            }
        }, 200);

    }


    @Override
    public void onClick(View v) {
        testDiagonalLineLayout();

    }

    private void testDiagonalLineLayout() {
        DiagonalLineLayout diagonalLineLayout = (DiagonalLineLayout) findViewById(R.id.diagonal_line);

        String str = "标签";
        for (int i = 0; i < random.nextInt(); i++) {
            str = str + str;
        }

        if (str.length() > 10) {
            str = str.substring(0, 10);
        }

        TextView textView = (TextView) mInflater.inflate(R.layout.listview_item_header, null);
        textView.setText(str);
        diagonalLineLayout.addView(textView);

    }

    private void testAutoLineLayout() {
        AutoLineLayout autoLineLayoutTest = (AutoLineLayout) findViewById(R.id.diagonal_line);


        String str = "标签";
        for (int i = 0; i < random.nextInt(); i++) {
            str = str + str;
        }

        if (str.length() > 10) {
            str = str.substring(0, 10);
        }

        TextView textView = (TextView) mInflater.inflate(R.layout.listview_item_header, null);
        textView.setText(str);
        autoLineLayoutTest.addView(textView);

    }
}
