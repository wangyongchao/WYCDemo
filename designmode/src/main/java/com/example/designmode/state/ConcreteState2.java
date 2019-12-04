package com.example.designmode.state;

public class ConcreteState2 extends State {

    @Override
    public void handle1() {

        //设置状态为state1，过渡到state1
        super.mContext.setCurrentState(Context.STATE1);
        super.mContext.handle1();

    }

    @Override
    public void handle2() {
        //本状态下必须处理的逻辑
        System.out.println("handle2");
    }
}
