
package com.weishop.test.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;

public class AnimatorActivity extends Activity implements View.OnClickListener {

    private TextView mTestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animator);
        mTestText = (TextView) findViewById(R.id.testText);
        findViewById(R.id.start).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "alpha", 1f, 0f, 1f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "rotation", 0f, 360f);
//        float curTranslationX = mTestText.getTranslationX();
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "translationX", curTranslationX, -500f, curTranslationX);
//        objectAnimator.setDuration(5000);
//
//        objectAnimator.start();

        ObjectAnimator moveIn = ObjectAnimator.ofFloat(mTestText, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mTestText, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mTestText, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();

    }
}
