package com.kttest2.ktclass

import com.kttest2.ktclass.Button.ButtonState

class ClassTest {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            constructTest()
            args.map {  }


        }

        fun eval(e: Expr): Int =
                when (e) {
                    is Expr.Num -> e.value
                    is Expr.Sum -> e.left + e.right
                }

        fun constructTest() {
            var user = User("zhangsan")
            println("${user.nickName}")

        }


        /**
         * 测试密封类
         */
        fun testSealed(){
            
        }


    }
}