package com.kttest

import com.kttest2.Color
import com.kttest2.zhinengzhuanhuan.Expr
import com.kttest2.zhinengzhuanhuan.Num
import com.kttest2.zhinengzhuanhuan.Sum


class KTBasic {


    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
//            val eval = evalWithLogging(Sum((Sum(Num(1), Num(2))), Num(4)))
//            println(eval)
            val notDigit = recognize('r')
            println(notDigit)


        }

        fun mix(c1: Color, c2: Color) {
            when (setOf(c1, c2)) {
                setOf(Color.ORANGE, Color.GREEN) -> "yewllow"
                setOf(Color.ORANGE, Color.GREEN) -> "dfadsf"
                else -> throw Exception()
            }
        }

        fun mixOptimized(c1: Color, c2: Color) =
                when {
                    //没有提供表达式参数，布尔表达式就是参数
                    (c1 == Color.GREEN && c2 == Color.ORANGE) -> "sdfadsf"
                    else -> throw Exception()
                }

        //代码块函数体
        fun max(a: Int, b: Int): Int {
            return if (a > b) a else b
        }

        //表达式函数体
//        fun max(a: Int, b: Int) = if (a > b) a else b

//        fun eval(e: Expr):Int{
//
//            if(e is Num){
        //智能转换
//                return e.value
//            }
//
//            if(e is Sum){
//                return  eval(e.left)+ eval(e.right)
//            }
//
//            throw IllegalArgumentException("unknown exception")
//
//        }

        //表达式函数体
//        fun eval(e: Expr): Int =
//                if (e is Num) {
//                    e.value
//                } else if (e is Sum) {
//                    eval(e.left) + eval(e.right)
//                } else {
//                    throw IllegalArgumentException("unknown exception")
//                }


        //使用when表达式
        fun eval(e: Expr): Int =
                when (e) {
                    is Num ->
                        e.value
                    is Sum ->
                        eval(e.left) + eval(e.right)
                    else ->
                        throw IllegalArgumentException("unknown exception")

                }

        //if 和when使用代码块作为函数体
        fun evalWithLogging(e: Expr): Int =
                when (e) {
                    is Num -> {
                        println("num ${e.value}")
                        e.value //代码块中最后一句就是返回结果
                    }
                    is Sum -> {
                        val left = evalWithLogging(e.left)
                        var right = evalWithLogging(e.right)
                        println("sum : $left+$right")
                        left + right
                    }
                    else -> {
                        throw java.lang.IllegalArgumentException("unknown exception")
                    }

                }

        fun fizzBuzz(i: Int) = when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Bizz"
            else -> i
        }

        fun testFor() {
            for (i in 1..100) {
                println(fizzBuzz(i))
            }

            for (i in 100 downTo 1 step 2) {
                println(fizzBuzz(i))
            }
        }

        /**
         * in 判断是否在某个区间
         */
        fun isNotDigit(c: Char) = c !in '0'..'9'

        /**
         * in 可以用在when当判断条件
         */
        fun recognize(c: Char) = when (c) {
            in '0'..'9' -> "it is a digit!"
            in 'a'..'z', in 'A'..'Z' -> "it is a letter"
            else -> "i dont know"
        }


    }

}