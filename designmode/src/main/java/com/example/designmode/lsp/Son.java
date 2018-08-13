package com.example.designmode.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongchao on 2018/7/31.
 */

public class Son extends Father {

    //放大输入参数类型
    public Collection doSomething(Map map) {
        System.out.println("child excute");
        return map.values();
    }
}
