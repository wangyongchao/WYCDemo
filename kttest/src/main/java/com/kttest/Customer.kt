package com.kttest

/**
 * 如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值
 * 就是创建类的时候不需要指定值  var customer = Customer()

 */
class Customer(val customerName: String = "333") {

    init {
        println("init ${customerName}")
    }
}