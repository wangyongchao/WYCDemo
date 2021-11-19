package com.weishop.test.util;

import android.content.Context;
import android.os.Looper;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by wangy on 2018/11/13.
 */

public class AppUtils {
    public static String COMMON_TAG = "common_tag";
    private static String TAG = "AppUtils";
    private static final String CPU_NAME_REGEX = "cpu[0-9]+";
    private static final String CPU_LOCATION = "/sys/devices/system/cpu/";
    public static final int _1MB = 1024 * 1024;
    public static final int _1GB = _1MB * 1024;


    /**
     * 获取cpu内核数
     */
    public void getCpuCount() {
        File[] cpus = null;
        try {
            File cpuInfo = new File(CPU_LOCATION);
            final Pattern cpuNamePattern = Pattern.compile(CPU_NAME_REGEX);
            cpus = cpuInfo.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return cpuNamePattern.matcher(s).matches();
                }
            });
        } catch (Throwable t) {
            if (Log.isLoggable(TAG, Log.ERROR)) {
                Log.e(TAG, "Failed to calculate accurate cpu count", t);
            }
        }
        int cpuCount = cpus != null ? cpus.length : 0;
        int availableProcessors = Math.max(1, Runtime.getRuntime().availableProcessors());

    }

    /**
     * 转换为M
     *
     * @param context
     * @param bytes
     * @return
     */
    private String toMb(Context context, int bytes) {
        return Formatter.formatFileSize(context, bytes);
    }

    public static boolean isMainThread(){
        return Looper.getMainLooper()==Looper.myLooper();
    }


}
