package com.weishop.test.util;

import android.app.Activity;
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
}
