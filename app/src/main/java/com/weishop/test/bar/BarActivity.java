package com.weishop.test.bar;

import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.util.StatusBarUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        toolbar.setBackgroundColor(getColor(R.color.white));
        StatusBarUtils.setStatusBarColor(this,R.color.white,true,true);
    }


    @Override
    public void onClick(View v) {
    }
}