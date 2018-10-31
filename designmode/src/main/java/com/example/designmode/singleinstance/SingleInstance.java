package com.example.designmode.singleinstance;

/**
 * Created by wangyongchao on 2018/8/19.
 */

public class SingleInstance {
    private final static SingleInstance singleInstance = new SingleInstance();

    private SingleInstance() {
    }

    private SingleInstance getInstance() {

        return singleInstance;
    }

}
