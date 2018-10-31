package com.example.designmode.test;

/**
 * Created by wangyongchao on 2018/8/19.
 */

public class Share {
    private boolean status;

    public void changeStatus() {
        status = true;
    }

    public void run() {
        if (status) {
            System.out.println("run..........");
        }
    }

}
