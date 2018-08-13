package com.example.arithmetic.linear;

import java.util.Arrays;

/**
 * Created by wangyongchao on 2018/7/5.
 * 自定义栈结构
 */

public class MyStack<E> {
    private Object[] elements;
    private int elementCount;

    public MyStack() {
        this(10);
    }

    public MyStack(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
        elements = new Object[initialCapacity];
    }

    public E push(E item) {
        if (elementCount + 1 > elements.length) {
            elements = Arrays.copyOf(elements, elements.length + elements.length / 2);
        }
        elements[elementCount++] = item;
        return item;
    }

    public E pop() {
        if (elementCount <= 0) {
            throw new IllegalArgumentException("the stack is Empty");
        }
        elementCount--;
        E element = (E) elements[elementCount];
        elements[elementCount] = null;
        return element;
    }


}
