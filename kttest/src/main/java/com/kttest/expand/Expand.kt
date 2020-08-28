package com.kttest.expand

class Expand {


    val <T> List<T>.lastIndex: Int
        get() = size - 1

    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // “this”对应该列表
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun test() {



    }

    fun printFunctionType() {
        println("Class method")
    }

    fun Any?.toString(): String {
        if (this == null) return "null"
        // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
        // 解析为 Any 类的成员函数
        return toString()
    }


}