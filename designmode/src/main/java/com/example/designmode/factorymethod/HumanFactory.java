package com.example.designmode.factorymethod;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public class HumanFactory extends AbstractHumanFactory {

    @Override
    public <T extends Human> T createHuman(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
