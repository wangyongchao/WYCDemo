package com.weishop.test.webview;

import android.content.Context;
import android.content.res.Resources;

import java.lang.reflect.Field;

public class ResourcesHelper {

    public static void replaceContextRes(Context context) {

        if (context.getResources() instanceof ReplaceResources) {
            return;
        }

        if (context != null && context.getClass().getName().endsWith("ContextImpl")) {

            try {
                Class<?> mClass = context.getClass();
                Field field = mClass.getDeclaredField("mResources");
                field.setAccessible(true);
                Resources res = (Resources) field.get(context);

                if (!(res instanceof ReplaceResources)) {
                    res = new ReplaceResources(res);
                    field.set(context, res);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
