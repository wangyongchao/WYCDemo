package com.example.designmode.state;

public class Context {
    public final static State STATE1 = new ConcreteState1();
    public final static State STATE2 = new ConcreteState2();

    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    //设置当前状态
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        //切换状态
        this.currentState.setContext(this);
    }

    public void handle1() {
        this.currentState.handle1();
    }

    public void handle2() {
        this.currentState.handle2();
    }

}
