package com.kttest2.ktclass.construct

/**
 *
 * 可以在主构造方法中声明默认值,这样创建对象的时候可以不用传递全部参数
 */
class User2 constructor(var nickname: String="default", val isSubscribed: Boolean = true) {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var user = User2("zhangsan",false)
            var user2 = User2("zhangsan",isSubscribed = false)//可以显示的标明名称
            var user3 = User2()//kotlin 会默认生成一个无参的构造方法来使用所有的默认值。
            println("${user3.nickname},${user3.isSubscribed}")

        }
    }
}



