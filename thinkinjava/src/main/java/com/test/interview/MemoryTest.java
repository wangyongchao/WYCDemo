package com.test.interview;

import java.util.ArrayList;

public class MemoryTest {
    private static int count = 0;

    public static void main(String[] args) {
        a();
    }

    private static void a() {
        ArrayList list = new ArrayList();
        while(true){
            list.add("dsfadsf");
        }
    }

    private static void b() {
        count++;
        a();
    }
}
