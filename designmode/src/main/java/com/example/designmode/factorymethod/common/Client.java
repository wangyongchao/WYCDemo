package com.example.designmode.factorymethod.common;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product1 = creator.createProduct(ConcreteProduct1.class);
    }
}
