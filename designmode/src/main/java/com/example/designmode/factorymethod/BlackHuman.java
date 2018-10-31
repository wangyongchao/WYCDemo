package com.example.designmode.factorymethod;

import com.example.designmode.factorymethod.Human;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黑色皮肤");
    }

    @Override
    public void talk() {
        System.out.println("黑人说话");

    }
}
