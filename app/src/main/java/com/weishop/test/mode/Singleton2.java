package com.weishop.test.mode;

/**
 * Created by wangyongchao on 17/3/21.
 */

public class Singleton2 {
    private static Singleton2 instance = null;

    private Singleton2() {
    }


    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
