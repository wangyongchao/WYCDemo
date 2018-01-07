package com.test;

/**
 * Created by wangyongchao on 2017/8/10.
 */

/**
 *
 */
public class SingleLazy {
    private static SingleLazy single = null;

    private SingleLazy() {
    }

    /**
     * 线程不安全
     *
     * @return
     */
    public static SingleLazy getInstance() {
        if (single == null) {
            single = new SingleLazy();
        }
        return single;
    }

    /**
     * 线程安全
     *
     * @return
     */
    public static synchronized SingleLazy getInstance_safe() {
        if (single == null) {
            single = new SingleLazy();
        }
        return single;
    }


    /**
     * 线程安全高效,双重判断
     *
     * @return
     */
    public static SingleLazy getInstance_safe_fficient() {
        if (single == null) {

            synchronized (SingleLazy.class) {
                if (single == null) {
                    single = new SingleLazy();
                }

            }
        }
        return single;
    }


}
