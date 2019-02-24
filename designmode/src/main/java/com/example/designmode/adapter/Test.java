package com.example.designmode.adapter;

public class Test {
    public static void main(String[] args) {
        Target t = new ConcreteTarget();
        t.request();

        Target adapter = new Adapter();
        adapter.request();

    }
}
