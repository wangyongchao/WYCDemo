package com.example.designmode.test;

/**
 * Created by wangyongchao on 2018/8/19.
 */

public class Test {
    public static void main(String[] args) {
        int endFrame=279;
        int startFrame=240;
        int frameRate=30;
        long frameDuration = endFrame - startFrame;
        long duration = (long) (frameDuration / (float) frameRate * 1000);
        System.out.println("duration="+duration);

    }


}
