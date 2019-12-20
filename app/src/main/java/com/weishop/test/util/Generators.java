package com.weishop.test.util;

import com.weishop.test.data.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 对象生成器，非常有用的工具类
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
        List<Student> tellers = new ArrayList<Student>();
        Generators.fill(tellers, Student.generator, 4);
    }
}
