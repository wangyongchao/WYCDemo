package com.example.designmode.simplefactory;

import com.example.designmode.simplefactory.driver.CarFactory;
import com.example.designmode.simplefactory.driver.Driver;

public class Client {

    public static void main(String[] args) {

        Api obj = Factory.create(2);

        obj = Factory.create(3);


        Driver driver = new Driver();
        driver.drive(CarFactory.product(1));

    }

}
