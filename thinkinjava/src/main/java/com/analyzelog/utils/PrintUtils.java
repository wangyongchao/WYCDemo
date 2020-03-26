package com.analyzelog.utils;

import java.util.List;

public class PrintUtils {

    public static <T> void printList(List<T> objects) {
        for (int i = 0; i < objects.size(); i++) {
            System.out.println(objects.get(i).toString());
        }

    }
}
