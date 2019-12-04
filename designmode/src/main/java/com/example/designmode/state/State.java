package com.example.designmode.state;

public abstract class State {
    //环境角色供子类访问
    protected Context mContext;

    public void setContext(Context context) {
        this.mContext = context;
    }

    public abstract void handle1();

    public abstract void handle2();


}
