package com.example.designmode.iterator.define;

import com.example.designmode.iterator.demo.IProject;
import com.example.designmode.iterator.demo.IProjectIterator;
import com.example.designmode.iterator.demo.Project;

public class Test {
    public static void main(String[] args) {
//声明出容器
        Aggregate agg = new ConcreteAggregate();
//产生对象数据放进去
        agg.add("abc");
        agg.add("aaa");
        agg.add("1234");
//遍历一下
        Iterator iterator = agg.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

