package com.example.designmode.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GamePlayIH implements InvocationHandler {
    Object object;


    public GamePlayIH(Object object) {
        this.object = object;
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//        System.out.println("o="+o+",method="+method+",objects="+objects);
        System.out.println("invoke method="+method.getName());
        Object result = method.invoke(object, objects);
        return result;
    }
}
