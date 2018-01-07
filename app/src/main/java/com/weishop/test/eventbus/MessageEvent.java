package com.weishop.test.eventbus;

public class MessageEvent {
    public final String name;
    public final String password;

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }
}