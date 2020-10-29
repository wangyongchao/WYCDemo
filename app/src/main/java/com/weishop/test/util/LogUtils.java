package com.weishop.test.util;

import android.util.Log;

public class LogUtils {

    public static void d(String message) {
        Log.d(TestUtils.TAG, message);
    }

    public static void e(String message) {
        Log.e(TestUtils.TAG, message);
    }
}
