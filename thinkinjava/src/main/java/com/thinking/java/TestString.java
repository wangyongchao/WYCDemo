
package com.thinking.java;

import java.util.Date;

/**
 * Created by wangyongchao on 16/3/14.
 */
public class TestString extends Date {

    private String name;

    public void testString() {

        System.out.println(super.getClass().getName());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
