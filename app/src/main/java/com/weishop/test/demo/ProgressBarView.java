
package com.weishop.test.demo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;


/**
 * 进度条
 */
public class ProgressBarView extends RelativeLayout {
    private int totalHeight;
    private Context mContext;
    int startMoveProgress;
    private int coverHeight;//被遮住部分的高度
    private int barHeight;
    private View progressBar;
    private View thumbMonkey;
    private int thumbMonkeyMarginBottom;

    public ProgressBarView(Context context) {
        this(context, null);
    }

    public ProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ProgressBarView(@NonNull Context context, @Nullable AttributeSet attrs,
                           int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();

    }


    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.live_business_vote_bar_view, this);
        progressBar = findViewById(R.id.pb_vote_bar_graph_bar);
        thumbMonkey=findViewById(R.id.live_business_img_vote_thumb_monkey);

        thumbMonkeyMarginBottom = TestUtils.dip2px(getContext(), 44);
        totalHeight = TestUtils.dip2px(getContext(), 200);
        coverHeight = TestUtils.dip2px(getContext(), 50 - 26);
        barHeight = totalHeight - coverHeight;

    }

    public void setProgress(int progress) {
        RelativeLayout.LayoutParams progressBarLayoutParams =
                (LayoutParams) progressBar.getLayoutParams();
        int factHeight = (int) (barHeight * progress / 100f);
        LogUtils.d("setProgress progress=" + progress + ",factHeight=" + factHeight);

        progressBarLayoutParams.height = factHeight+coverHeight;
        progressBar.setLayoutParams(progressBarLayoutParams);

        RelativeLayout.LayoutParams monkeyLayoutParams = (LayoutParams) thumbMonkey.getLayoutParams();
        monkeyLayoutParams.bottomMargin=thumbMonkeyMarginBottom+factHeight;
        thumbMonkey.setLayoutParams(monkeyLayoutParams);


    }

    public void setProgressSmooth(int progress) {

        ObjectAnimator animation = ObjectAnimator.ofInt(this, "progress", 100,progress);
        // 1 second
        animation.setDuration(1000);
        //先加速在减速
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

    }

}
