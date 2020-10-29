package com.kttest

import com.kttest2.Color
import com.kttest2.Person
import java.lang.Exception


class KTBasic {



    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
           println(Color.ORANGE.rgb())


        }

        fun mix(c1:Color,c2:Color){
            when(setOf(c1,c2)){
                setOf(Color.ORANGE,Color.GREEN)->"yewllow"
                setOf(Color.ORANGE,Color.GREEN)->"dfadsf"
                else -> throw Exception()
            }
        }

        //代码块函数体
        fun max(a: Int, b: Int): Int {
            return if (a > b) a else b
        }

        //表达式函数体
//        fun max(a: Int, b: Int) = if (a > b) a else b



    }

}