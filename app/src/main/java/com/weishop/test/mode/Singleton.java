package com.weishop.test.mode;

/**
 * Created by wangyongchao on 17/3/21.
 */

public class Singleton {
    private final static Singleton instance = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }
}
