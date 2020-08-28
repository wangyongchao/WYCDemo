package com.kttest

class Person public constructor(name: String) {
    val firstProperty = "First property: $name".also(::println) //属性初始化器

    init {
        //初始化块
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    /**
     * 次构造函数必须委托给主构造函数
     */
    constructor(age: Int) : this("${age}") {
        println("constructor 222")
    }

    /**
     * 可以间接委托
     */
    constructor(age: Long) : this(3) {
        println("constructor 333")
    }


}