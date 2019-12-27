package com.thinking.java.generics;//: generics/Manipulation.java
// {CompileTimeError} (Won't compile)

import java.lang.reflect.Type;

class Manipulator<T extends HasF> {
    private T obj;

    public Manipulator(T x) {
        obj = x;
    }

    // Error: cannot find symbol: method f():
    public void manipulate() {
        obj.f();
        Type parameterizedType = ReflectTypeUtils.getParameterizedType(Manipulator.class,
                this.getClass(), 0);
        System.out.println("parameterizedType=" + parameterizedType.getTypeName());
    }
}

public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator =
                new Manipulator<HasF>(hf);
        manipulator.manipulate();
    }
} ///:~
