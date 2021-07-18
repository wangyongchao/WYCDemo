package com.kttest2.ktclass.`object`

class A {

    /**
     * 类A中不允许有静态方法或者静态变量,可以借助于伴生对象
     * 伴生对象，相当于java中的静态变量和静态方法 A.a
     */
    companion object {
        var a = ""
        fun bar() {
            println("bar")
        }
    }


}