package com.example.designmode.state;

public class ConcreteState1 extends State {

    @Override
    public void handle1() {
        //本状态下必须处理的逻辑
        System.out.println("handle1");

    }

    @Override
    public void handle2() {
        super.mContext.setCurrentState(Context.STATE2);
        super.mContext.handle2();
    }


}
