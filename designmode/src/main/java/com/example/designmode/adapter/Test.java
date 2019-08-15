package com.example.designmode.adapter;

import com.example.designmode.adapter.define.Adapter;
import com.example.designmode.adapter.define.ConcreteTarget;
import com.example.designmode.adapter.define.Target;

public class Test {
    public static void main(String[] args) {
        Target t = new ConcreteTarget();
        t.request();

        Target adapter = new Adapter();
        adapter.request();

    }
}
