package com.thinking.java.generics;//: generics/LimitsOfInference.java

import net.mindview.util.New;

import java.util.List;
import java.util.Map;

public class LimitsOfInference {
    static void
    f(Map<String, List<? extends Fibonacci>> petPeople) {
    }

    public static void main(String[] args) {
        f(New.<String, List<? extends Fibonacci>>map()); // Does not compile
    }
} ///:~
