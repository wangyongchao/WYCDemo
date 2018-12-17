package com.example.designmode.command;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 执行命令
     */
    public void action() {
        command.execute();
    }

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.setCommand(new ConcreteCommand1());
        invoker.action();
    }

}
