package com.example.designmode.simplefactory.factorymethod;

/**
 * @author：wangyongchao on 17/5/7 09:20
 */
public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> c);
}
