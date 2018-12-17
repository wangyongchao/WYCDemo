package com.example.designmode.command;

public class ConcreteCommand2 extends Command {
    private Receiver receiver;

    public ConcreteCommand2() {
        super(new ConcreteReciver2());
    }

    public ConcreteCommand2(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.receiver.dosomething();

    }
}
