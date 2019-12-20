package com.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest implements Listener {

    public static void main(String[] args) {
        String a = null;
        Listener proxyTest = new ProxyTest();
        Class<Listener>[] interfaces = (Class<Listener>[]) proxyTest.getClass().getInterfaces();

        Listener proxyInstance = (Listener) Proxy.newProxyInstance(proxyTest.getClass().getClassLoader(),
                interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("调用之前");
                if (a == null) {
                    return null;
                }
                return method.invoke(proxyTest, objects);
            }
        });
        proxyInstance.onListener(new Person());

    }

    @Override
    public void onListener(Person person) {
        System.out.println("onListener=" + person.getName());
    }
}
