package com.example.designmode.factorymethod;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黄色皮肤");
    }

    @Override
    public void talk() {
        System.out.println("黄种人说话");

    }
}
