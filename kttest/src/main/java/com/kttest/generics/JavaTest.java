package com.kttest.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 上界通配符主要用于读数据，下界通配符主要用于写数据。
 * T 是一个 确定的 类型，通常用于泛型类和泛型方法的定义，？是一个 不确定 的类型，通常用于泛型方法的调用代码和形参
 */
class JavaTest {
    public static void main(String[] args) {

    }

    private static <T extends Number> void test1(List<T> dst, List<T> src) {


    }

    private <T> void test2(List<? extends T> dst, List<? extends T> src) {
    }

    private void test(List<? super Object> dst, List<String> src) {
        Object o = dst.get(0);
        for (String t : src) {
            dst.add(t);
        }
    }

    public static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.getLegCounts();
        }
        return retVal;
    }


    public static int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.getLegCounts();
        }
        return retVal;
    }

    void demo(Source<String> stringSource) {
        Source< ? extends Object> objectSource=stringSource;
    }
}
