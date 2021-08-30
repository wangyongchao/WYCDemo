package com.kttest2.operatorOverrideAppoint

import java.math.BigDecimal

/**
 * 运算符重载
 */
class OperatorOverrideTetst {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            testCompareOperator()
        }

        /**
         * 重载比较运算符
         * kotlin 实现了Comparable接口的都可以调用<,>,<=和>=,不用重载运算符
         */
        fun testCompareOperator(){
            var p1=Person("Alice","Cmith")
            var p2=Person("Bob","Dohnson")
            println(p1 < p2)

        }

        /**
         * 重载一元运算符，没有任何参数
         * +a unaryPlus
         * -a unaryMinus
         * !a not
         * ++a a++ inc
         * --a a-- dec
         */
        fun  testUnaryOperator(){
            var point=Point(10,34);
            println(-point)

            var bd =BigDecimal.ZERO
            println(bd++)
            println(++bd)


        }

        /**
         * 位运算
         * shl --带符号左移
         * shr --带符号右移
         * ushr --无符号右移
         * and -- 按位与
         * or --按位或
         * xor --按位异或
         * inv -- 按位取反
         */
        fun testBitOperation(){
            println(0x0F and 0xF0)
            println(0x0F or 0xF0)
            println(0x01 shl  4)

        }

        /**
         * 二元运算重载
         * a*b times
         * a/b div
         * a % b mod
         * a+b plus
         * a-b minus
         */
        fun testBinaryArithmetic() {
            var p1 = Point(20, 40)
            var p2 = Point(20, 40)
            println(p1 + p2)

            //定义扩展函数
            println(p1 * 0.5)
            println(p1 + p2)
            println(p1 * 0.2f)

            println('a'*3)

            //复合运算符
            p1+=p2
            println(p1)

            val numbers =ArrayList<Int>()
            numbers+=43
            println(numbers)

            var list = arrayListOf(2, 3)
            println("@"+Integer.toHexString(System.identityHashCode(list)))
            list+=4
            println(list)
            println("@"+Integer.toHexString(System.identityHashCode(list)))
            val plusList = list + listOf(8, 9) //会生成新的集合
            println("@" + Integer.toHexString(System.identityHashCode(plusList)))
            println(plusList)

        }
    }
}

/**
 * 一个字符重复3次转换成string
 */
operator fun Char.times(count:Int):String{
    return toString().repeat(count)
}

operator fun BigDecimal.inc()=this+BigDecimal.ONE


