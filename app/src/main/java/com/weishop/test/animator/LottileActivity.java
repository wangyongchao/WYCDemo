
package com.weishop.test.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieImageAsset;
import com.weishop.test.R;
import com.weishop.test.util.LogUtils;
import com.weishop.test.util.TestUtils;

public class LottileActivity extends Activity implements View.OnClickListener {
    private static final String LOTTIE_RES_ASSETS_ROOTDIR = "interaction/";
    private LottieAnimationView lottieAnimationView;
    private View pkView;
    private View contentView;
    private int marginTop;
    private int marginbottom;
    private int marginHorizontal;
    ProgressView progressView;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lottile);
        lottieAnimationView = findViewById(R.id.lottile_view);
        pkView = findViewById(R.id.pkview);
        layoutParams = (RelativeLayout.LayoutParams) pkView.getLayoutParams();
        contentView = findViewById(R.id.content_view);
        progressView = findViewById(R.id.energy_progress_seek_bar);
        findViewById(R.id.start).setOnClickListener(this);

        lottieAnimationView.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestUtils.getProperty(LottileActivity.this);
                Drawable drawable = getResources().getDrawable(R.drawable.chengse);
                LogUtils.d(drawable.getIntrinsicHeight() + "," + drawable.getIntrinsicWidth());

            }
        }, 2000);
//        Drawable drawable = getResources().getDrawable(R.drawable.chengse);
//        ViewGroup.LayoutParams layoutParams = seekBar.getLayoutParams();
//        layoutParams.width=drawable.getIntrinsicWidth();
//        layoutParams.height=drawable.getIntrinsicHeight();
//        seekBar.setLayoutParams(layoutParams);

    }

    /**
     * img1 左边小的
     * img3 左边大的
     * img6 右边小的
     * img2 右边大的
     * img0 右边
     * img4 右边
     */
    private void startAnimation() {
//        lottieAnimationView.getViewTreeObserver().addOnGlobalLayoutListener(new
//        ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int height = lottieAnimationView.getHeight();
//
//                RelativeLayout.LayoutParams pkViewParams =
//                        (RelativeLayout.LayoutParams) pkView.getLayoutParams();
//                pkViewParams.topMargin = marginTop + (int) (height * 370f / 640);
//                pkView.setLayoutParams(pkViewParams);
//
//                lottieAnimationView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
        String bubbleResPath = LOTTIE_RES_ASSETS_ROOTDIR + "pkresult/images";
        String bubbleJsonPath = LOTTIE_RES_ASSETS_ROOTDIR + "pkresult/data.json";
        String[] targetFileNames = {"img_3.png", "img_6.png",
                "img_8.png", "img_9.png", "img_10.png",
                "img_11.png", "img_12.png", "img_13.png",
                "img_15.png", "img_16.png", "img_17.png",
                "img_18.png", "img_19.png", "img_20.png",
                "img_21.png", "img_22.png", "img_23.png",
                "img_24.png", "img_25.png", "img_26.png",
                "img_27.png", "img_28.png", "img_29.png",
                "img_31.png", "img_32.png", "img_33.png",
                "img_34.png", "img_35.png", "img_36.png",
                "img_37.png", "img_38.png", "img_39.png",
                "img_14.png", "img_30.png", "img_40.png"
        };
        final LottieEffectInfo bubbleEffectInfo = new LottieEffectInfo(bubbleResPath,
                bubbleJsonPath);
        lottieAnimationView.setAnimation(bubbleEffectInfo.getJsonReader(this), "3v3");
        lottieAnimationView.useHardwareAcceleration(true);
        ImageAssetDelegate imageAssetDelegate = new ImageAssetDelegate() {
            @Override
            public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
                String fileName = lottieImageAsset.getFileName();
                LogUtils.d("fileName=" + fileName + ",width=" + lottieImageAsset.getWidth() + "," +
                        "height=" + lottieImageAsset.getHeight());
                return bubbleEffectInfo.fetchBitmapFromAssets(lottieAnimationView,
                        lottieImageAsset.getFileName(),
                        lottieImageAsset.getId(), lottieImageAsset.getWidth(),
                        lottieImageAsset.getHeight(), LottileActivity.this);
            }
        };
        lottieAnimationView.setImageAssetDelegate(imageAssetDelegate);
        lottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                int width = lottieAnimationView.getWidth();
//                int height = lottieAnimationView.getHeight();
//                float scale = lottieAnimationView.getScale();
//                LogUtils.d("onAnimationUpdate loop width=" + width + ",height=" + height + "," +
//                        "scale=" + scale);
            }
        });
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                int width = lottieAnimationView.getWidth();
                int height = lottieAnimationView.getHeight();
                LogUtils.d("onAnimationStart loop width=" + width + ",height=" + height);
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                int width = lottieAnimationView.getWidth();
                int height = lottieAnimationView.getHeight();
                Drawable drawable = lottieAnimationView.getDrawable();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                int intrinsicWidth = drawable.getIntrinsicWidth();
                LogUtils.d("onAnimationEnd loop width=" + width + ",height=" + height +
                        ",intrinsicHeight=" + intrinsicHeight + ",intrinsicWidth=" + intrinsicWidth);
            }


        });
        lottieAnimationView.playAnimation();
    }


    @Override
    public void onClick(View v) {
        layoutParams.leftMargin = TestUtils.dip2px(this, 20);
        pkView.requestLayout();
    }


}
