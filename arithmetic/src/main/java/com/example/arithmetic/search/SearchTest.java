package com.example.arithmetic.search;


import java.util.HashMap;

public class SearchTest {

    public static void main(String[] args) {
        HashMap<Person, String> map = new HashMap<>();
        map.put(new Person(1), "1");
        map.put(new Person(2), "2");
        map.put(new Person(3), "3");
        map.put(new Person(4), "4");
        map.put(new Person(5), "5");

        String s = map.get(new Person(3));
        System.out.println(s);

    }
}
