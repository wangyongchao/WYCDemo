package com.example.designmode.proxy.definition;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        before();
        subject.request();
        after();

    }

    /**
     * 预处理
     */
    private void before() {
    }

    /**
     * 善后处理
     */
    private void after() {
    }

}
