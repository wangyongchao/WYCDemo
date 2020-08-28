
package com.weishop.test.demo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.weishop.test.R;

public class DemoActivity extends Activity {

    private ProgressBarView progressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desniy);
        progressView = findViewById(R.id.progress_view);
        findViewById(R.id.btn_incre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressBy(10);
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int per = 50;
                progressView.setProgressSmooth(per);
            }
        });


    }

    public void setProgressBy(int diff) {

        ObjectAnimator animation = ObjectAnimator.ofInt(progressView, "progressBy", diff);
        // 1 second
        animation.setDuration(1000);
        //先加速在减速
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

    }

    /**
     * 设置ProgressBar的进度（平滑的增长）
     *
     * @param progress 取值0-100
     */
    public void setProgressSmooth(int progress) {
        ObjectAnimator animation = ObjectAnimator.ofInt(progressView, "progress", 100,30);
        // 1 second
        animation.setDuration(1000);
        //先加速在减速
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
