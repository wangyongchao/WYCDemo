package com.weishop.test.memory;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongchao on 17/6/16.
 */

public class SingleInstance {
    private static List<Context> contexts = new ArrayList();
    private static SingleInstance INSTANCE;

    private SingleInstance() {
    }

    public static SingleInstance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingleInstance();
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
