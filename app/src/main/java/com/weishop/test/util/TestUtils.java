
package com.weishop.test.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);
        int screenHeight = dm.heightPixels;
        int screenWidth = dm.widthPixels;
        float density = dm.density;
        int densityDpi = dm.densityDpi;

        LogUtils.d("screenHeight=" + screenHeight + ",screenWidth="
                + screenWidth + ",densityDpi=" + densityDpi + ",density=" + density);

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
     * 获取分辨率
     */
    public static void getRatio(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;


        DisplayMetrics metrics1 = new DisplayMetrics();
        metrics1 = context.getApplicationContext().getResources().getDisplayMetrics();
        int width1 = metrics1.widthPixels;
        int height1 = metrics1.heightPixels;

        LogUtils.d("width=" + width + ",height="
                + height + ",width1=" + width1 + ",height1=" + height1);


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

        Log.d(TAG, "当前可扩展的最大内存maxMemory=" + maxMemory
                + ",当前堆内存大小totalMemory=" + totalMemory + ",当前堆可用内存freeMemory=" + freeMemory);

        //系统内存相关属性
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        float totalMem = caculateMunit(info.totalMem);
        float availMem = caculateMunit(info.availMem);
        boolean lowMemory = info.lowMemory;
        float threshold = caculateMunit(info.threshold);

        Log.d(TAG, "totalMem=" + totalMem + "，availMem=" + availMem +
                ",lowMemory=" + lowMemory + ",threshold=" + threshold);


    }

    /**
     * MemoryInfo.totalMem:系统总的内存大小
     * MemoryInfo.availMem:系统当前可用的内存大小
     * MemoryInfo.lowMemory:现在系统是否处于低内存的情况
     * MemoryInfo.threshold：低内存的临界值，低于这个值的情况下，系统会有限杀死后台进程或者一些无关联的进程。
     * <p>
     * Runtime.maxMemory:app堆内存可以扩展的最大内存
     * Runtime.freeMemory:当前堆内存可用的内存，没有扩展的情况下
     * Runtime.totalMemory:当前堆内存的大小,在没有扩展的情况下。
     * totalMemory-freeMemory:就是当前已经占用的堆内存的大小
     *
     * @param context
     */
    public static void getAppHeapMemoryInfo(Context context) {
        float maxMemory = caculateMunit(Runtime.getRuntime().maxMemory());
        float totalMemory = caculateMunit(Runtime.getRuntime().totalMemory());
        float freeMemory = caculateMunit(Runtime.getRuntime().freeMemory());
        float used = totalMemory - freeMemory;

        Log.d(TAG, "当前可扩展的最大内存maxMemory=" + maxMemory
                + "M,当前堆内存大小totalMemory=" + totalMemory
                + "M,当前堆可用内存freeMemory=" + freeMemory + ",已经使用的内存=" + used);


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
                LogUtils.d(name + "=" + strAction + ",x=" + x
                        + ",y=" + y + ",rawX=" + rawX + ",rawY=" + rawY + other);
            }
        }
    }

    public static void printMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthmode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = View.MeasureSpec.getSize(widthMeasureSpec);

        int heightmode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = View.MeasureSpec.getSize(heightMeasureSpec);

        LogUtils.d("widthmode=" + TestUtils.getStringMeasureMod(widthmode) + ",widthsize=" + widthsize
                + ",heightmode=" + TestUtils.getStringMeasureMod(heightmode) + ",heightsize=" + heightsize);
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

    public static boolean createDumpFile(Context context) {
        String LOG_PATH = "/dump.gc/";
        boolean bool = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ssss", Locale.getDefault());
        String createTime = sdf.format(new Date(System.currentTimeMillis()));
        String state = android.os.Environment.getExternalStorageState();

        // 判断SdCard是否存在并且是可用的
        if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + LOG_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }

            String hprofPath = file.getAbsolutePath();
            if (!hprofPath.endsWith("/")) {
                hprofPath += "/";
            }

            hprofPath += createTime + ".hprof";
            try {
                LogUtils.d("hprofPath=" + hprofPath);
                android.os.Debug.dumpHprofData(hprofPath);
                bool = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            bool = false;
            Log.d("nsz", "no sdcard!");
        }

        return bool;
    }

    public static  String getExceptionDetail(StackTraceElement[] stackTrace ) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = stackTrace.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append("\t"+stackTrace[i].toString()+"\n");
        }
        return stringBuffer.toString();
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
