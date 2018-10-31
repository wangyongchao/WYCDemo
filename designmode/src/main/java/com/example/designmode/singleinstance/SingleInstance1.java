package com.example.designmode.singleinstance;

/**
 * Created by wangyongchao on 2018/8/19.
 */

public class SingleInstance1 {
    private static volatile SingleInstance1 singleInstance = null;//禁止指令重排序

    private SingleInstance1() {
    }

    private synchronized SingleInstance1 getInstance() {
        if (singleInstance == null) {
            singleInstance = new SingleInstance1();
        }

        return singleInstance;
    }

    /**
     * 双重判断
     *
     * @return
     */
    private SingleInstance1 getInstance1() {
        if (singleInstance == null) {
            synchronized (SingleInstance1.class) {
                if (singleInstance == null) {
                    singleInstance = new SingleInstance1();
                }
            }
        }
        return singleInstance;
    }

}
