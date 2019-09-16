package com.example.arithmetic;

public class Test {

    public static void main(String[] args) {
        int a = 0x01;
        int b = 0x02;
        int c = 0x03;
        int d = a | b | c;

        System.out.println((d & a)==a);



    }
}
