package com.thinking.java.generics;//: generics/ErasedTypeEquivalence.java

import java.util.ArrayList;

public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        Class<ArrayList> arrayListClass = ArrayList.class;
        System.out.println(c1 == c2);
        System.out.println(arrayListClass == c2);
    }
} /* Output:
true
*///:~
