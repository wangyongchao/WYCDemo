
package com.weishop.test.animator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

/**
 * 进度条
 */
public class ProgressView extends RelativeLayout {
    private View progressBackGroudView;
    private View progressView;
    private View progressThumbView;
    private int totalWidth;
    private float ratio;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ProgressView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_progress, this);
        progressBackGroudView = findViewById(R.id.progress_backgroud);
        totalWidth = TestUtils.dip2px(getContext(),230);
        ratio = totalWidth / 100f;
        progressView = findViewById(R.id.progress);
        progressThumbView = findViewById(R.id.progress_thumb);
    }

    public void setProgress(int progress) {
        int progressWidth = (int) (progress * ratio);
        ViewGroup.LayoutParams progressViewLayoutParams = progressView.getLayoutParams();
        progressViewLayoutParams.width = progressWidth;
        progressView.setLayoutParams(progressViewLayoutParams);

        RelativeLayout.LayoutParams thumbViewLayoutParams =
                (LayoutParams) progressThumbView.getLayoutParams();
        thumbViewLayoutParams.leftMargin = progressWidth-TestUtils.dip2px(getContext(),5);
        progressThumbView.setLayoutParams(thumbViewLayoutParams);

    }


}
