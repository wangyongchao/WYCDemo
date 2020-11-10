package com.kttest

import com.kttest2.Color
import com.kttest2.Person
import java.lang.Exception
import java.util.*


class KTBasic {



    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
          println(isLetter('e'))


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

        //for 循环可以迭代字符区间或数字区间
        fun testForMap(){
            var binaryReps=TreeMap<Char,String>()
            for(c in 'A'.. 'F'){
                val binary=Integer.toBinaryString(c.toInt())
                binaryReps[c]=binary
            }
            for ((letter,binary) in binaryReps){
                println("$letter=$binary")
            }
        }

        /**
         * 使用for循环迭代list
         */
        fun testForList(){
            var list= arrayListOf("10","11","20")
            for ((index,element) in list.withIndex()){
                println("$index=$element")
            }
        }

        /**
         * 检查集合或区间的成员
         */
        fun isLetter(c:Char)=c in 'a' ..'z' || c in 'A' .. 'Z'




    }

}