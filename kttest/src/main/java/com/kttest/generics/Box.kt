package com.kttest.generics

class Box<T>(t: T) {
    var value = t

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var box = Box<Int>(3)
            var box1 =Box(4)

        }
    }
}