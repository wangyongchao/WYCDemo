package com.example.designmode.factorymethod;

/**
 * Created by wangyongchao on 2018/8/20.
 */

public class NvWa {
    public static void main(String[] args) {
        AbstractHumanFactory humanFactory = new HumanFactory();
        WhiteHuman whiteHuman = humanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();

        BlackHuman blackHuman = humanFactory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();

        YellowHuman yellowHuman = humanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();


    }
}
