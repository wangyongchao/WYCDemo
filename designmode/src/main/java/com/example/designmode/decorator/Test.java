package com.example.designmode.decorator;

/**
 * Created by wangyongchao on 2018/8/19.
 */

public class Test {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
//第一次修饰
        component = new ConcreteDecorator1(component);
//第二次修饰
        component = new ConcreteDecorator2(component);
//修饰后运行
        component.operate();

    }


}
