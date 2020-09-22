package com.weishop.test.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TextScaleView extends AppCompatTextView {

    private int mValue;

    public TextScaleView(Context context) {
        this(context, null);
    }

    public TextScaleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setValue(int value) {
        setText(String.valueOf(value));
    }


    public void smoothAddValue(int addValue) {
        if (addValue <= 0) {
            return;
        }

        ObjectAnimator scalexObjectAnimator = ObjectAnimator.ofFloat(this, "scaleX",
                1f,
                1.6f, 1f);
        ObjectAnimator scaleyObjectAnimator = ObjectAnimator.ofFloat(this, "scaleY",
                0f,
                1.6f, 1f);

        ObjectAnimator setTextAnimator = ObjectAnimator.ofInt(this, "value", mValue,
                mValue + addValue);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
        animatorSet.setDuration(300);
        animatorSet.play(scalexObjectAnimator).with(scaleyObjectAnimator).with(setTextAnimator);
        animatorSet.start();
        this.mValue = this.mValue + addValue;
    }

}
