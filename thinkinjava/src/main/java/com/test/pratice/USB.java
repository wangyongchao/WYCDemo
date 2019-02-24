package com.test.pratice;

interface USB {
    public abstract void start();
}

class Outer {
    public void fun() {
        final int i = 1; // 被匿名内部类访问的局部变量必须被final修饰
        new USB() {
            @Override
            public void start() {
                System.out.println("local_var_i=" + i);
            }
        }.start();
    }
}