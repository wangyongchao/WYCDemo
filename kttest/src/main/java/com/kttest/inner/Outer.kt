package com.kttest.inner

class Outer {
    private val bar: Int = 1

    /**
     * 标记为 inner 的嵌套类能够访问其外部类的成员
     */
    inner class Nested {
        fun foo() = bar
    }
}