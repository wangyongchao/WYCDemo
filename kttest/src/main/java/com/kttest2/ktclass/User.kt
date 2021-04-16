package com.kttest2.ktclass

/**
 * constructor 初始化主构造方法或从构造方法
 * var 不用重新声明变量
 */
open class User constructor(var name: String, val isSubcribed: Boolean = true) {//主构造方法

    init {
        //类创建的时候会被执行一次
        println("init")
    }

    var address: String = "unspecified"
        set(value: String) {
            println("""Address was changed for $name: "$field"->"$value".""".trimIndent())
            field = value//会无限调用set方法，需要用field
        }
        get() {
            return field
        }


}