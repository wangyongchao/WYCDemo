package com.weishop.test.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.weishop.test.R;

public class StatusBarUtils {

    public static void setTransparentBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//标识window负责绘制system bar的背景
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));
        }
    }

    public static void setTransparentBar(Activity activity, boolean isTextBlick) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isTextBlick) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
            setWindowFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (isTextBlick) {
                    activity.getWindow().setStatusBarColor(0xffE0E0E0);
                } else {
                    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
            } else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }

        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * @param context
     * @param colorId
     * @param iscolorIdWhite 如果时白色 或者接近白色  需要填true。  进行5.0上的适配
     */
    public static void setStatusBarColor(Activity context, int colorId, boolean isTextBlick, boolean iscolorIdWhite) {
        if (context == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                //小于6.0 不支持改变字体颜色,所以不能使用纯白色colore
                if (iscolorIdWhite && isTextBlick && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    colorId = R.color.td_gray_d8;
                }

                Window window = context.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(context.getResources().getColor(colorId));
                if (isTextBlick) {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
