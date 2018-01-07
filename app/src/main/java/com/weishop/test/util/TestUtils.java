
package com.weishop.test.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by wangyongchao on 15/11/18.
 */
public class TestUtils {

    public static List<Context> contexts = new ArrayList<>();
    public static List<View> views = new ArrayList<>();


    public static boolean OPEN = false;

    public static void getProperty(Activity context) {
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int contentTop = context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        // statusBarHeight是上面所求的状态栏的高度
        int titleBarHeight = contentTop - statusBarHeight;

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;// 不包括虚拟按键，view显示的高度
        float density = dm.density;
        int densityDpi = dm.densityDpi;

        System.out.println("titlebarheight=" + titleBarHeight + ",statusBarHeight="
                + statusBarHeight + ",width=" + width + ",height=" + height + ",density=" + density + ",densityDpi=" + densityDpi);

    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        return dm.heightPixels;

    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        return dm.widthPixels;

    }

    public static void getMemoryInfo(Context context) {
        long maxMemory = (Runtime.getRuntime().maxMemory() / (1024 * 1024));


        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        long availMem = info.availMem / (1024 * 1024);


        System.out.println("Max memory is " + maxMemory + "MB" + ",availMem=" + availMem + "MB");

    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static void printEvent(MotionEvent ev, String name, HashMap<String, String> values) {
        if (TestUtils.OPEN) {
            float x = ev.getX();
            float y = ev.getY();
            float rawX = ev.getRawX();
            float rawY = ev.getRawY();
            String strAction = null;
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    strAction = "ACTION_DOWN";
                    break;
                case MotionEvent.ACTION_MOVE:
                    strAction = "ACTION_MOVE";
                    break;
                case MotionEvent.ACTION_UP:
                    strAction = "ACTION_UP";
                    break;
                case MotionEvent.ACTION_CANCEL:
                    strAction = "ACTION_CANCEL";
                    break;
            }
            String other = ",";
            if (values != null && values.size() > 0) {
                Set<String> set = values.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    String value = values.get(next);
                    other = other + next + "=" + value + ",";
                }
            }
            if (TestUtils.OPEN) {
                System.out.println(name + "=" + strAction + ",x=" + x
                        + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY + other);
            }
        }
    }

    public static String getStringMeasureMod(int mode) {
        String strMode = null;
        switch (mode) {
            case View.MeasureSpec.EXACTLY:
                strMode = "EXACTLY";
                break;
            case View.MeasureSpec.AT_MOST:
                strMode = "AT_MOST";
                break;
            case View.MeasureSpec.UNSPECIFIED:
                strMode = "UNSPECIFIED";
                break;
        }

        return strMode;

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
