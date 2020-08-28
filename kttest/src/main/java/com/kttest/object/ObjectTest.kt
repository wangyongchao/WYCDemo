package com.kttest.`object`


/**
 * 对象表达式是在使用他们的地方立即执行（及初始化）的； 匿名内部类
对象声明是在第一次被访问到时延迟初始化的； 类似于单例
伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配。
 */

class ObjectTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ab: A = object : A(1), B {
                override val y = 12
                override fun testb() {
                    TODO("Not yet implemented")
                }
            }

            var a=MyClass
            a.create()
        }

        fun foo() {
            val adHoc = object {
                var x: Int = 3
                var y: Int = 4
            }
            println(adHoc.x)
        }
    }
}