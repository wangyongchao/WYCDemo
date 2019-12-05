package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by wangyongchao on 16/12/8.
 */

public class Lambda {

    public static void main(String[] args) {
//        Listener listener = new Listener() {
//
//            @Override
//            public void onListener(Person person) {
//                person.getId();
//
//            }
//        };
        Lambda lambda = new Lambda();

        lambda.setListener(new Listener() {
            @Override
            public void onListener(Person person) {
                System.out.println(person.getId());
            }
        });

        lambda.setListener(person -> System.out.println(person.getId()));
        lambda.setListener(Person::getId);

        Supplier supplier = "dSfD"::toLowerCase;
        System.out.println(supplier.get());


    }

    public void setListener(Listener listener) {
        Person person = new Person();
        person.setId(232);
        listener.onListener(person);

    }
}
