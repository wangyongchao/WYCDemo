
package com.weishop.test.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

public class ShangTaiActivity extends Activity implements View.OnClickListener {
    private static final String LOTTIE_RES_ASSETS_ROOTDIR = "interaction/";
    private View zbst1;
    private View zbst2;
    private LottieAnimationView feichuan_view1;
    private View feichuan_view2;
    private RelativeLayout bottom_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangtai);
        zbst1 = findViewById(R.id.zbst_view1);
        zbst2 = findViewById(R.id.zbst_view2);
        feichuan_view1 = findViewById(R.id.feichuan_view1);
        feichuan_view2 = findViewById(R.id.feichuan_view2);
        bottom_layout = findViewById(R.id.bottom_layout);
        findViewById(R.id.start).setOnClickListener(this);

    }


    /**
     * 飞船循环动画
     */
    private void startFeiChuanLoopAnimation() {
        String bubbleResPath = LOTTIE_RES_ASSETS_ROOTDIR + "feichuanloop/images";
        String bubbleJsonPath = LOTTIE_RES_ASSETS_ROOTDIR + "feichuanloop/data.json";
        final LottieEffectInfo bubbleEffectInfo = new LottieEffectInfo(bubbleResPath,
                bubbleJsonPath);
        feichuan_view1.setAnimation(bubbleEffectInfo.getJsonReader(this), null);
        feichuan_view1.useHardwareAcceleration(true);
        ImageAssetDelegate imageAssetDelegate = new ImageAssetDelegate() {
            @Override
            public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
                String fileName = lottieImageAsset.getFileName();
                LogUtils.d("fileName=" + fileName + ",width=" + lottieImageAsset.getWidth() + "," +
                        "height=" + lottieImageAsset.getHeight());
                return bubbleEffectInfo.fetchBitmapFromAssets(feichuan_view1,
                        lottieImageAsset.getFileName(),
                        lottieImageAsset.getId(), lottieImageAsset.getWidth(),
                        lottieImageAsset.getHeight(), ShangTaiActivity.this);
            }
        };
        feichuan_view1.setImageAssetDelegate(imageAssetDelegate);
        feichuan_view1.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int width = feichuan_view1.getWidth();
                int height = feichuan_view1.getHeight();
                float scale = feichuan_view1.getScale();
                LogUtils.d("onAnimationUpdate loop width=" + width + ",height=" + height + "," +
                        "scale=" + scale);
            }
        });
        feichuan_view1.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                int width = feichuan_view1.getWidth();
                int height = feichuan_view1.getHeight();
                LogUtils.d("onAnimationStart loop width=" + width + ",height=" + height);
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                int width = feichuan_view1.getWidth();
                int height = feichuan_view1.getHeight();
                LogUtils.d("onAnimationEnd loop width=" + width + ",height=" + height);
            }


        });
        feichuan_view1.setRepeatCount(LottieDrawable.INFINITE);
        feichuan_view1.playAnimation();
    }


    @Override
    public void onClick(View v) {
        startFeichuanTranslate();
        startZbstScale();

    }

    /**
     * 飞船上台
     */
    private void startShangTaiAnimation() {


    }

    /**
     * 底部视频框缩小
     */
    private void startBottomLayoutAnimation() {
        ViewWrapper viewWrapper = new ViewWrapper(bottom_layout);
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewWrapper, "height",
                TestUtils.dip2px(this, 106),
                TestUtils.dip2px(this, 36));
        animator.setDuration(500);
        animator.start();

    }


    private class ViewWrapper {
        private View mView;

        public ViewWrapper(View target) {
            mView = target;
        }

        public float getWidth() {
            return mView.getLayoutParams().width;
        }

        public void setWidth(float width) {
            mView.getLayoutParams().width = (int) width;
            mView.requestLayout();
        }

        public float getHeight() {
            return mView.getLayoutParams().height;
        }

        public void setHeight(float height) {
            mView.getLayoutParams().height = (int) height;
            mView.requestLayout();
        }
    }

    /**
     * 准备上台文字动画
     */
    private void startZbstScale() {
        ObjectAnimator scaleXObjectAnimator = ObjectAnimator.ofFloat(zbst1, "scaleX", 0f, 1f);
        ObjectAnimator scaleYObjectAnimator = ObjectAnimator.ofFloat(zbst1, "scaleY", 0f, 1f);
        scaleXObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                float scaleX = zbst1.getScaleX();
                LogUtils.d("animatedValue=" + animatedValue + ",scaleX=" + scaleX);
            }
        });
        scaleYObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                float scaleY = zbst1.getScaleY();
                LogUtils.d("animatedValue=" + animatedValue + ",scaleY=" + scaleY);
            }
        });

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleXObjectAnimator).with(scaleYObjectAnimator);
        animSet.setDuration(300);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        animSet.start();
    }


    /**
     * 飞船从屏幕外飞进屏幕
     */
    private void startFeichuanTranslate() {
        float translationY = feichuan_view1.getTranslationY();
        int from = feichuan_view1.getBottom() + feichuan_view1.getHeight();

        LogUtils.d("translationY=" + translationY + ",from=" + from);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(feichuan_view1, "translationY",
                from, translationY);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                float translationY = feichuan_view1.getTranslationY();
                LogUtils.d("animatedValue=" + animatedValue + ",translationY=" + translationY);
            }
        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startFeiChuanLoopAnimation();
                int width = feichuan_view1.getWidth();
                int height = feichuan_view1.getHeight();
                LogUtils.d("onAnimationEnd traslate width=" + width + ",height=" + height);
                objectAnimator.removeAllListeners();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                LogUtils.d("onAnimationStart");
            }
        });
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }


}
