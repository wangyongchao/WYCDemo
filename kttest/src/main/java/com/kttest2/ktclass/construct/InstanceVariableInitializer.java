package com.kttest2.ktclass.construct;

public class InstanceVariableInitializer {

//    {
    // 构造代码块和实例遍历初始化是按照执行顺序执行的。
    //构造代码块在构造函数初始化之前执行
//        System.out.println("i="+i); 报错
//    }


    public InstanceVariableInitializer() {
        System.out.println("构造函数初始化i="+i+",j="+j);
    }

    private int i = 1;
    private int j = i + 1;

    {
        System.out.println("第一个构造代码块i="+i+",j="+j);
        i=i+3;
    }

    {
        System.out.println("第二个构造代码块i="+i+",j="+j);
    }


    public static void main(String[] args) {
        InstanceVariableInitializer instanceVariableInitializer =new InstanceVariableInitializer();

    }
}
