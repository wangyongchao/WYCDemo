package com.kttest.data

/**
 * 编译器自动从主构造函数中声明的所有属性导出
 */
data class Person1(val name: String) {
    var age: Int = 0

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }
}