
package com.weishop.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weishop.test.custom.CustomActivity;
import com.weishop.test.custom.MyActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private EditText editText;

    private TextView mTextview;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.linear_btn).setOnClickListener(this);
        findViewById(R.id.frame_btn).setOnClickListener(this);
        findViewById(R.id.relative_btn).setOnClickListener(this);
        findViewById(R.id.myview_btn).setOnClickListener(this);
        findViewById(R.id.animate_btn).setOnClickListener(this);
        findViewById(R.id.listview_btn).setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageview);
        mTextview = (TextView) findViewById(R.id.text);
        mTextview.setOnTouchListener(new OnPressTouchListener());
        mTextview.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this, "dfasdf",Toast.LENGTH_LONG).show();

    }

    class OnPressTouchListener implements View.OnTouchListener {
        private Drawable stateDrawable;
        private Drawable orginalDrawable;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    orginalDrawable = v.getBackground();
                    if (orginalDrawable != null) {
                        orginalDrawable.setColorFilter(Color.parseColor("#33F13232"), PorterDuff.Mode.SRC_ATOP);
                    } else {
                        stateDrawable = new ColorDrawable(Color.parseColor("#33F13232"));
                        v.setBackgroundDrawable(stateDrawable);
                    }
                    v.invalidate();
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (orginalDrawable != null) {
                        orginalDrawable.clearColorFilter();
                    }else {
                        v.setBackgroundDrawable(null);
                    }
                    v.invalidate();
                    break;
            }
            return false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity onDestroy");
    }
}
