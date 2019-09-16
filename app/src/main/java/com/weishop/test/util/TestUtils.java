
package com.weishop.test.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
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

    public final static String TAG = "wangyongchao";

    public static Context applicationContext;

    public static List<Context> contexts = new ArrayList<>();
    public static List<View> views = new ArrayList<>();
    public final static String[] imageUrls = new String[]{
            "https://img-my.csdn.net/uploads/201508/05/1438760758_3497.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760758_6667.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760757_3588.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760756_3304.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760755_6715.jpeg",
            "https://img-my.csdn.net/uploads/201508/05/1438760726_5120.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760726_8364.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760725_4031.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760724_9463.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760724_2371.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760707_4653.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760706_6864.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760706_9279.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760704_2341.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760704_5707.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760685_5091.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760685_4444.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760684_8827.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760683_3691.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760683_7315.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760663_7318.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760662_3454.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760662_5113.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760661_3305.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760661_7416.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760589_2946.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760589_1100.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760588_8297.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760587_2575.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760587_8906.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760550_2875.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760550_9517.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760549_7093.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760549_1352.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760548_2780.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760531_1776.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760531_1380.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760530_4944.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760530_5750.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760529_3289.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760500_7871.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760500_6063.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760499_6304.jpeg",
            "https://img-my.csdn.net/uploads/201508/05/1438760499_5081.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760498_7007.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760478_3128.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760478_6766.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760477_1358.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760477_3540.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760476_1240.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760446_7993.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760446_3641.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760445_3283.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760444_8623.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760444_6822.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760422_2224.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760421_2824.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760420_2660.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760420_7188.jpg",
            "https://img-my.csdn.net/uploads/201508/05/1438760419_4123.jpg",
    };


    public static boolean OPEN = true;

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

        Log.d(TAG,"titlebarheight=" + titleBarHeight + ",statusBarHeight="
                + statusBarHeight + ",width=" + width + ",height=" + height + ",density=" + density + ",densityDpi=" + densityDpi);

    }

    public static int getScreenHeight(Context activity) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        return dm.heightPixels;

    }

    public static int getScreenWidth(Context activity) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        return dm.widthPixels;

    }

    /**
     * MemoryInfo.totalMem:系统总的内存大小
     * MemoryInfo.availMem:系统当前可用的内存大小
     * MemoryInfo.lowMemory:现在系统是否处于低内存的情况
     * MemoryInfo.threshold：低内存的临界值，低于这个值的情况下，系统会有限杀死后台进程或者一些无关联的进程。
     * <p>
     * Runtime.maxMemory:当前堆内存可以扩展的最大内存
     * Runtime.freeMemory:当前堆内存可用的内存，没有扩展的情况下
     * Runtime.totalMemory:当前堆内存的大小
     *
     * @param context
     */
    public static void getMemoryInfo(Context context) {
        float maxMemory = caculateMunit(Runtime.getRuntime().maxMemory());
        float totalMemory = caculateMunit(Runtime.getRuntime().totalMemory());
        float freeMemory = caculateMunit(Runtime.getRuntime().freeMemory());

        Log.d(TAG,"maxMemory=" + maxMemory + ",totalMemory=" + totalMemory + ",freeMemory=" + freeMemory);

        //系统内存相关属性
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        float totalMem = caculateMunit(info.totalMem);
        float availMem = caculateMunit(info.availMem);
        boolean lowMemory = info.lowMemory;
        float threshold = caculateMunit(info.threshold);

        Log.d(TAG,"totalMem=" + totalMem + "，availMem=" + availMem +
                ",lowMemory=" + lowMemory + ",threshold=" + threshold);


    }

    /**
     * 转化为M
     *
     * @param value
     * @return
     */
    public static float caculateMunit(long value) {
        float result = value / (1024 * 1024f);
        return result;
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
