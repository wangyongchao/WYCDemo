package com.example.designmode.lsp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongchao on 2018/7/31.
 */

public class Client {
    public static void main(String[] args) {
        invoker();
    }

    public static void invoker() {
        //父类存在的地方，子类就应该能够存在
        Son f = new Son();
        HashMap map = new HashMap();
        f.doSomething(map);
    }
}
