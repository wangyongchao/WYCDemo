package com.kttest2.ktclass.gettersetter

class User(val name:String) {
    var  address:String="unspecified"
        /**
         * 每次设置值的时候都会调用set方法，使用filed方法访问支持字段的值。
         */
        set(value) {
        println(""" Adrress was changed for $name : "$field" -> "$value"""".trimIndent())
            field=value

    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val user=User("zhangsan")
            user.address="郑州市高新区升龙又一场"
            println(user.address)
        }
    }
}