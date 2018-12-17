package com.example.designmode.proxy;

import com.example.designmode.proxy.aop.GamePlayIH;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) throws Throwable {
        IGamePlayer player = new GamePlayer();
        InvocationHandler handler = new GamePlayIH(player);

        System.out.println("开始时间是：2009-8-25 10:45");

        ClassLoader cl = player.getClass().getClassLoader();

        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, new Class[]{IGamePlayer.class}, handler);//登录
        proxy.login("zhangSan", "password");

        proxy.killBoss();

        proxy.upgrade();

        System.out.println("结束时间是：2009-8-26 03:40");
    }
}
