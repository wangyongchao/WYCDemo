package com.example.designmode.chain;

public class Client {

    public static void main(String[] args) {

        Handler handler1 = new ConcretHandler1();
        Handler handler2 = new ConcretHandler1();
        Handler handler3 = new ConcretHandler1();

        handler1.setNext(handler2);
        handler2.setNext(handler3);

        handler1.handleMessage(new Request(new Level()));

    }
}
