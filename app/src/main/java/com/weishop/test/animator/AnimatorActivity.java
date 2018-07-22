
package com.weishop.test.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.weishop.test.R;

public class AnimatorActivity extends Activity implements View.OnClickListener {

    private AdvTextSwitcher advTextSwitcher;
    private TextView mTestText;
    private Switcher switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animator);
        advTextSwitcher = (AdvTextSwitcher) findViewById(R.id.advTextSwitcher);
        mTestText = (TextView) findViewById(R.id.testText);
        findViewById(R.id.start).setOnClickListener(this);


        initSwitcher();

    }

    private void initSwitcher() {
        String[] texts = {"Anne: Great!", "Cathy: I do not think so.", "Jimmy: Cloning your repo...", "Aoi: This bug " +
                "disappeared!"};
        //Give them to AdvTextSwitcher
        advTextSwitcher.setTexts(texts);
        //Manually switch to the next text in the String array.
//        advTextSwitcher.next();
        //Switch to the previous one.
//        advTextSwitcher.previous();

        //Auto switch between texts every 5000ms.
        switcher = new Switcher(advTextSwitcher, 2000);
        //Pause
//        switcher.pause();
//        //Or use switcher in only one line...
//        new Switcher().attach(advTextSwitcher).setDuration(5000).start();
//
//        //Want to know which text is clicked?
//        advTextSwitcher.setCallback(new AdvTextSwitcher.Callback() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(AnimatorActivity.this, "ITEM@" + position + " Clicked!", Toast.LENGTH_SHORT).show();
//            }
//
//        });
    }


    private void startAnimation() {
        //        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "alpha", 1f, 0f, 1f);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "rotation", 0f, 360f);
//        float curTranslationX = mTestText.getTranslationX();
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "translationX", curTranslationX, -500f,
// curTranslationX);
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


    @Override
    public void onClick(View v) {
        switcher.start();
//        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(AnimatorActivity.this, R.anim
//                .text_scale);
//        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(AnimatorActivity.this, R.anim
// .text_alpha);

//        AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(AnimatorActivity.this, R.anim
// .text_set_out);
//        TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(AnimatorActivity
// .this, R.anim.text_translate);
//        mTestText.startAnimation(animationSet);

    }
}
