package com.test;

/**
 * Created by wangyongchao on 2017/8/10.
 * 饿汉式
 */

public class SingleHunger {
    private final static SingleHunger single = new SingleHunger();

    private SingleHunger() {
    }

    public SingleHunger getInstance() {
        return single;
    }

}
