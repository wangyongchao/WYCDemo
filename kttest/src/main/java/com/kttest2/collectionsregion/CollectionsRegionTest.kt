package com.kttest2.collectionsregion

import com.kttest2.operatoroverride.Point
import com.kttest2.operatoroverride.get
import java.time.LocalDate
import java.util.*

/**
 * 集合与区间
 */
class CollectionsRegionTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            testRangeTo()

        }

        /**
         * rangeTo约定".."
         * start..end-> start.rangeTo(end)
         * rangeTo运算符低于算术运算符 1..n+1 先计算n+1
         *
         */
        fun testRangeTo(){
           var now=LocalDate.now()
            val vacation=now .. now.plusDays(10) //now.rangeTo (now.plusDays(10))
            println(now.plusWeeks(1) in vacation)

            (0..5).forEach {
                println(it)
            }


        }

        /**
         * in 运算符 对应的函数式contains
         * a in c, c.contains(a)
         */
        fun testOperatorIn(){
            val rectangle=Rectangle(Point(10,30),Point(50,100))
            println(rectangle.contains(Point(20,40)))
            //in 右边的对象会调用contains函数，in 左边的对象作为函数的入参
            println(Point(20,40) in rectangle)

        }


        /**
         * 通过下标访问 下标对应的函数是 get，set
         */
        fun accessByIndex() {
            var map = mutableMapOf<Int, String>()
            map[1] = "1" //数组形式的下标方法，会被转换为get方法的调用

            var point = Point(23, 45)
            println(point[0])//会被转换为get方法的调用

            val mutablePoint = MutablePoint(23, 44)
            mutablePoint[0] = 12
            println(mutablePoint)
        }


    }


}