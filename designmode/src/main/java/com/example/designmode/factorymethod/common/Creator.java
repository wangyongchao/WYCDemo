package com.example.designmode.factorymethod.common;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> c);
}
