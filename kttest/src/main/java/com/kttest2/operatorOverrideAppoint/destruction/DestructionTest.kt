package com.kttest2.operatorOverrideAppoint.destruction

import com.kttest2.operatorOverrideAppoint.Point


/**
 * 解构声明
 */
class DestructionTest {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            testDestruction()

        }

        /**
         * 调用 componentN
         * x=p.component1()
         * y=p.component2()
         *
         * 解构声明主要应用从一个函数返回多个值
         */
        fun testDestruction(){
            //数据类型 data class 会默认生成解构函数
            val p=Point(12,30)
            val (x,y)=p
            println("$x,$y")

            val data=NoData(23,454)
            val (a,b)=data
            println("$a,$b")
        }
    }
}