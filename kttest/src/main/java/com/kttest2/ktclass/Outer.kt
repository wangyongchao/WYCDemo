package com.kttest2.ktclass

class Outer {
    /**
     * class inner 相当于java static class，默认不持有外部类的引用
     * inner class 持有外部类的引用
     *
     */
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}