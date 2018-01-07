package com.example.designmode.simplefactory.driver;

/**
 * Created by wangyongchao on 17/4/26.
 */

public class CarFactory {

    public static Car product(int type) {
        switch (type) {
            case 1:
                return new BenCar();
            case 2:
                return new BMWCar();
        }

        return null;

    }


}
