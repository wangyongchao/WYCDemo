package com.test;

import java.math.BigDecimal;

public class BigDeciamlTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("-1.3223");
        System.out.println(bigDecimal.toString());
        int i = bigDecimal.compareTo(BigDecimal.ZERO);
        System.out.println("i=" + i);

    }
}
