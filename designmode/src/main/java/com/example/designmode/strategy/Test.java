package com.example.designmode.strategy;

public class Test {
    public static void main(String[] args) {
        Strategy concreteStrategy1 = new ConcreteStrategy1();
        Context context = new Context(concreteStrategy1);
        context.doAnything();
    }
}
