package com.kttest.visibility

open class Outer {
    private var a = 1
    protected open var b = 2
    internal val c = 3
    val d = 4 //默认都是public

    protected class Nested {
        public val e: Int = 5
    }


}