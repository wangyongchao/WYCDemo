package com.kttest2.ktclass.construct

/**
 * constructor(nickname: String) 主构造方法
 * constructor用来声明主构造方法或从构造方法。
 *
 *和java中一样，初始化代码块在构造函数初始化之前执行。
 *var _nickname: String 直接在主构造方法中声明属性
 */
open class User constructor(var _nickname: String) {
    private var nickName=_nickname

    init {
        //初始化代码块。主构造方法不能包含初始化代码，这就是为什么要使用初始化语句块的作用。
//        nickName=_nickname 也可以直接在字段声明的时候初始化
        println("init1 $nickName")
    }

    init {
        nickName="二个初始化块儿"
        println("init2 $nickName")
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            var user=User("zhangsan")
            println("${user._nickname}")

        }
    }
}



