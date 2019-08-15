package com.classloader.test;

public class Test1 {
    public static void main(String[] args) {

        int privateFlags = 0;
        int plag_drawn = 0x00000020;
        int plag_has_bounds = 0x00010150;
        System.out.println("plag_drawn=" + plag_drawn + ",plag_has_bounds=" + plag_has_bounds);
        privateFlags |= plag_drawn;
        privateFlags |= plag_has_bounds;

        System.out.println("result=" + privateFlags);
        boolean t = (privateFlags & (plag_drawn | plag_has_bounds)) == (plag_drawn | plag_has_bounds);
        System.out.println(t);

        privateFlags &= ~plag_drawn;
        System.out.println(privateFlags);


    }

}