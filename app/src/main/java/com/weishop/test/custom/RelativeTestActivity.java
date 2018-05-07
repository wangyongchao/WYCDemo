
package com.weishop.test.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.weishop.test.R;


public class RelativeTestActivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_relative);
        imageView = (ImageView) findViewById(R.id.imageview);

        Drawable drawable = getResources().getDrawable(R.drawable.user_poster_preview);

        int screenWidth = getScreenWidth();
//        int height = screenWidth * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth();

        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(layoutParams);
        imageView.setMaxHeight(drawable.getIntrinsicHeight());
        imageView.setMaxWidth(screenWidth);

    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context
                .WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.widthPixels;
    }
}
