package com.example.designmode.factorymethod;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public abstract class AbstractHumanFactory {
    public abstract <T extends Human> T createHuman(Class<T> clazz);
}
