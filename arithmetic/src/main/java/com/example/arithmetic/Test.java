package com.example.arithmetic;

public class Test {

    public static void main(String[] args) {
        int MODE_SHIFT = 3;
        int MODE_MASK = 0x3 << MODE_SHIFT;
        ;
        int EXACTLY = 1 << MODE_SHIFT;

        int size = 5;
        int mode = EXACTLY;

        int spec = (size & ~MODE_MASK) | (mode & MODE_MASK);

        int mode1 = (spec & MODE_MASK);

        int size1 = (spec & ~MODE_MASK);

        System.out.println((mode1==EXACTLY) + "," + size1);

    }


}
