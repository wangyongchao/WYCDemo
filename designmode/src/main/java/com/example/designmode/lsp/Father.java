package com.example.designmode.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongchao on 2018/7/31.
 */

public class Father {

    public Collection doSomething(HashMap map) {
        System.out.println("father execute");
        return map.values();
    }

}
