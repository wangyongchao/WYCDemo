package com.example.designmode.factorymethod.common;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public class ConcreteCreator extends Creator {
    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        T t = null;
        try {
            t = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
