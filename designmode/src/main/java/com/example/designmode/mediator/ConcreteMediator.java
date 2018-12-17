package com.example.designmode.mediator;

/**
 * 通用中介者
 */
public class ConcreteMediator extends Mediator {

    @Override
    public void doSomething1() {
        c1.selfMethod1();
        c2.selfMethod2();

    }

    @Override
    public void doSomething2() {
        c1.selfMethod1();
        c2.selfMethod2();

    }
}
