package com.weishop.test.performance.memory;

import android.content.Context;

import com.weishop.test.util.AppUtils;
import com.weishop.test.util.TestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 17/6/16.
 */

public class SingleInstance {
    private static List<Context> contexts = new ArrayList();
    private static SingleInstance INSTANCE;
    private final Context mContext;
    private byte[] a =new byte[100*AppUtils._1MB];


    private SingleInstance(Context context) {
        this.mContext = context;
    }

    public static SingleInstance getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SingleInstance(context);
        }
        return INSTANCE;
    }


    public void addContext(Context context) {
        contexts.add(context);
    }


    public void clear() {
        contexts.clear();
    }
}
