
package com.weishop.test.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieImageAsset;
import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class AnimatorActivity extends Activity implements View.OnClickListener {
    private static final String LOTTIE_RES_ASSETS_ROOTDIR = "interaction/";
    private TextView mTestText;
    private TextView mTestText1;
    private View textLayout;
    private LottieAnimationView bubbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_animator);
        bubbleView = findViewById(R.id.bubble);
        textLayout = findViewById(R.id.testText_layout);
        mTestText = (TextView) findViewById(R.id.testText);
        mTestText1 = (TextView) findViewById(R.id.testText1);
        findViewById(R.id.start).setOnClickListener(this);

        textLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                TestUtils.getProperty(AnimatorActivity.this);
            }
        }, 100);

    }

    private void startBubbleAnimation() {
        String bubbleResPath = LOTTIE_RES_ASSETS_ROOTDIR + "bubble_enter/images";
        String bubbleJsonPath = LOTTIE_RES_ASSETS_ROOTDIR + "bubble_enter/data.json";
        final LottieEffectInfo bubbleEffectInfo = new LottieEffectInfo(bubbleResPath,
                bubbleJsonPath);
        try {
            JSONObject jsonObject = new JSONObject(bubbleEffectInfo.getJsonStrFromAssets(this));
            bubbleView.setAnimation(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bubbleView.useExperimentalHardwareAcceleration(true);
        ImageAssetDelegate imageAssetDelegate = new ImageAssetDelegate() {
            @Override
            public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
                String fileName = lottieImageAsset.getFileName();
                return bubbleEffectInfo.fetchBitmapFromAssets(bubbleView,
                        lottieImageAsset.getFileName(),
                        lottieImageAsset.getId(), lottieImageAsset.getWidth(),
                        lottieImageAsset.getHeight(), AnimatorActivity.this);
            }
        };
        bubbleView.setImageAssetDelegate(imageAssetDelegate);
        bubbleView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        bubbleView.playAnimation();
    }


    @Override
    public void onClick(View v) {
        startAppearAnima();
    }

    private void testXml() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.translate);
        animator.setTarget(mTestText);
        animator.start();

    }

    private void testScore() {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(mTestText, "alpha", 0f, 1f);
        alphaObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                System.out.println("animatedValue=" + animatedValue);
            }
        });

        float translationY = mTestText.getTranslationY();
        System.out.println("translationY=" + translationY);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "translationY",
                translationY, -mTestText
                        .getLeft(), translationY);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                float translationX = mTestText.getTranslationX();
                System.out.println("animatedValue=" + animatedValue + ",translationX=" + translationX + ",getLeft=" +
                        mTestText.getLeft());
            }
        });
        animatorSet.play(alphaObjectAnimator).with(objectAnimator);
        animatorSet.setDuration(1000);
        animatorSet.start();

    }

    /**
     * 能量金币放出现效果
     */
    private void startAppearAnima() {

        float translationY = textLayout.getTranslationY();
        System.out.println("startAppearAnima translationY=" + translationY);
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(textLayout, "translationY",
                30, translationY);
        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(textLayout, "alpha", 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startScaleAnima();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                textLayout.setVisibility(View.VISIBLE);
            }
        });

        animatorSet.setDuration(300);
        animatorSet.play(translateAnimator).with(alphaObjectAnimator);
        animatorSet.start();


    }

    /**
     * 能量金币放大效果
     */
    private void startScaleAnima() {
        ObjectAnimator scalexGoldObjectAnimator = ObjectAnimator.ofFloat(mTestText, "scaleX", 0f,
                1.2f, 1f);
        ObjectAnimator scaleyGoldObjectAnimator = ObjectAnimator.ofFloat(mTestText, "scaleY", 0f,
                1.2f, 1f);


        ObjectAnimator scalexAnergyObjectAnimator = ObjectAnimator.ofFloat(mTestText1, "scaleX",
                0f, 1.2f, 1f);
        ObjectAnimator scaleyAnergyObjectAnimator = ObjectAnimator.ofFloat(mTestText1, "scaleY",
                0f, 1.2f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startDisappearAnima();

            }
        });
        animatorSet.setDuration(1000);
        animatorSet.play(scalexGoldObjectAnimator).with(scaleyGoldObjectAnimator)
                .with(scalexAnergyObjectAnimator).with(scaleyAnergyObjectAnimator);
        animatorSet.start();
    }

    /**
     * 能量金币放消失效果
     */
    private void startDisappearAnima() {

        float translationY = textLayout.getTranslationY();
        System.out.println("startDisappearAnima translationY=" + translationY);
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(textLayout, "translationY",
                translationY, -30);
        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(textLayout, "alpha", 1f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });

        animatorSet.setDuration(300);
        animatorSet.play(translateAnimator).with(alphaObjectAnimator);
        animatorSet.start();


    }


    /**
     * 组合动画
     */
    private void testAnimatorSet() {
        AnimatorSet animatorSet = new AnimatorSet();
        float translationX = mTestText.getTranslationX();
        float distance = mTestText.getLeft() + mTestText.getMeasuredWidth();
        //先从屏幕外移动
        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(mTestText, "translationX",
                -distance, translationX);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(mTestText, "rotation", 0f, 360f);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mTestText, "alpha", 0f, 1f);

        animatorSet.play(alphaAnimator).with(translateAnimator).before(rotateAnimator);
        //只监听某个listneener
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }
        });
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                System.out.println("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                System.out.println("onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                System.out.println("onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                System.out.println("onAnimationRepeat");
            }
        });
        animatorSet.setDuration(5000);
        animatorSet.start();

    }


    /**
     * 缩放
     */
    private void testScale() {
        //放大3倍，再还原
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "scaleY", 1f, 3f, 1f);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                float scaleY = mTestText.getScaleY();
                System.out.println("animatedValue=" + animatedValue + ",scaleY=" + scaleY);
            }
        });
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }

    /**
     * 移动
     */
    private void testTranslateY() {
        textLayout.setVisibility(View.VISIBLE);
        float translationY = textLayout.getTranslationY();
        System.out.println("translationY=" + translationY + ",getTop=" + textLayout.getTop());
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textLayout, "translationY",
                30, translationY);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                float translationY = textLayout.getTranslationY();
                System.out.println("animatedValue=" + animatedValue + ",translationY=" + translationY);
            }
        });
        objectAnimator.setDuration(300);
        objectAnimator.start();


    }

    /**
     * 移动
     */
    private void testTranslateX() {
        float translationX = mTestText.getTranslationX();
        System.out.println("translationX=" + translationX + ",getLeft=" + mTestText.getLeft());
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "translationX",
                translationX, -mTestText
                        .getLeft(), translationX);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                float translationX = mTestText.getTranslationX();
                System.out.println("animatedValue=" + animatedValue + ",translationX=" + translationX + ",getLeft=" +
                        mTestText.getLeft());
            }
        });
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }

    /**
     * setRotation()、getRotation()
     * 旋转
     */
    private void testRotation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "rotation", 0f, 360f);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                System.out.println("animatedValue=" + animatedValue);
            }
        });
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }


    /**
     * 渐变
     */
    private void testAlpha() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTestText, "alpha", 1f, 0f, 1f);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                System.out.println("animatedValue=" + animatedValue);
            }
        });
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }


    private void testValueAni() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 5f, 3f, 10f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                System.out.println("animatedValue=" + animatedValue);

            }
        });
        valueAnimator.start();
    }

}
