package com.test.pratice;

/**
 * Created by wangyongchao on 17/3/29.
 */

public class OuterClass {
    private int outer = 3;

    public void testOuter() {
        System.out.println("testOuter");
        final int i = 4;
        class MethodInnerClass {
            private int methodInner = 4;

            public void testMethodInner() {
                System.out.println("testMethodInner" + i);
            }

        }


    }


    protected class InnerClass {
        private int inner;

        public void testInner() {
            System.out.println("testInner=" + OuterClass.this.outer);
        }

    }


    public static void main(String[] args) {

    }


}
