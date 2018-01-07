package com.test;

import java.lang.reflect.Method;

/**
 * Created by wangyongchao on 17/1/19.
 */

public class GetSex {

    public static void main(String[] args) {

        try {
            Class clazz=Class.forName("com.test.TestArrayList");
            Object o = clazz.newInstance();
            Method method = clazz.getMethod("checkedAdd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
