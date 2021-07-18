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
        TypeClass typeClass = new TypeClass(obj);
        Type resultType = typeClass.getResultType();
        System.out.println("parameterizedType=" + resultType);
    }

    class TypeClass<T> implements  HasF{
        T t;

        public TypeClass(T t) {
            this.t = t;
        }

        public Type getResultType() {
            return t == null ? String.class :
                    ReflectTypeUtils.getParameterizedType(t.getClass(),
                            HasF.class, 0);
        }


        @Override
        public void f() {

        }
    }
}

public class Manipulation {
    public static void main(String[] args) {
        HasFChild hf = new HasFChild();
        Manipulator<HasFChild> manipulator =
                new Manipulator<HasFChild>(hf);
        manipulator.manipulate();
    }
} ///:~
