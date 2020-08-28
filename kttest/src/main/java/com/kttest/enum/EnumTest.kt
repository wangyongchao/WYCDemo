package com.kttest.enum

class EnumTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var color=Color.valueOf("RED")
            println(color)
        }
    }
}