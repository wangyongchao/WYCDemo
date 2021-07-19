package com.kttest2.ktclass.construct

/**
 * 构造函数私有化
 */
class Secretive private constructor(){


    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            var secretive= Secretive()
        }
    }
}