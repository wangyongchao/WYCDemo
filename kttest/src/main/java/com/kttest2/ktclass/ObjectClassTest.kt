package com.kttest2.ktclass

class ObjectClassTest {


    /**
     * 相当于静态内部类
     */
    companion object {
        @JvmStatic
        var a=3
        @JvmStatic
        fun main(args: Array<String>) {
            var obj=ObjectClassTest.Companion
            println(obj)
            var obj1=ObjectClassTest.Companion
            println(obj1)


        }

    }
}