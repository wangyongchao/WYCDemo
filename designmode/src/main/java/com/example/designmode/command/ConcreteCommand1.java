package com.example.designmode.command;

public class ConcreteCommand1 extends Command {

    public ConcreteCommand1() {
        super(new ConcreteReciver1());
    }


    public ConcreteCommand1(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.dosomething();

    }
}
